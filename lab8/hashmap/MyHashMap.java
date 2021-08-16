package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size; // number of elements in the map
    private double loadFactor;
    private HashSet<K> keys;

    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_MAX_LF = 0.75;


    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_MAX_LF);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAX_LF);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        size = 0;
        loadFactor = maxLoad;
        keys = new HashSet<K>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    @Override
    public void clear() {
        size = 0;
        keys = new HashSet<>();
        buckets = createTable(DEFAULT_INITIAL_SIZE);
    }

    @Override
    public boolean containsKey(K key) {
        return keySet().contains(key);
    }

    @Override
    public V get(K key) {
        Node n = getNode(key);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    private Node getNode(K key) {
        int index = findBucket(key);
        Collection<Node> list = buckets[index];
        if (list != null) {
            for (Node node : list) {
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }

        if ((double) size / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }
        size ++;
        keys.add(key);
        int index = findBucket(key);
        Collection<Node> bucketList = buckets[index];

        // insertion case
        if (bucketList == null) {
            bucketList = createBucket();
            buckets[index] = bucketList;
        }
        bucketList.add(createNode(key, value));
    }

    private void resize(int i) {
        Collection<Node>[] newBuckets = createTable(i);
        for (K key : keys) {
            int index = findBucket(key, newBuckets.length);
            if (newBuckets[index] == null) {
                newBuckets[index] = createBucket();
            }
            newBuckets[index].add(getNode(key));
        }
        this.buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private int findBucket(K key) {
        return findBucket(key, buckets.length);
    }

    private int findBucket(K key, int bucketsLength) {
        return Math.floorMod(key.hashCode(), bucketsLength);
    }

}
