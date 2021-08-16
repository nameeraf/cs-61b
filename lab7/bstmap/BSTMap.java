package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;

    private class BSTNode {
        private K key;
        private V val;
        private BSTNode left, right;
        private int size;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
            size = 1;
        }
    }


    /**
    * Removes all of the mappings from this map.
    */
    public void clear() {
        root = null;
    }

    /**
    * Returns true if this map contains a mapping for the specified key.
    */
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private boolean containsKeyHelper(K key, BSTNode n) {
        if (n == null) {
            return false;
        }
        int compareInt = key.compareTo(n.key);
        if (compareInt == 0) {
            return true;
        }
        if (compareInt > 0) {
            return containsKeyHelper(key, n.right);
        }
        else {
            return containsKeyHelper(key, n.left);
        }

    }

    /** Returns the value to which the specified key is mapped, or null if this
    * map contains no mapping for the key.
    */
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, BSTNode currNode) {
        if (currNode == null) {
            return null;
        }
        int compareInt = key.compareTo(currNode.key);
        if (compareInt == 0) {
            return currNode.val;
        }
        if (compareInt > 0) {
            return getHelper(key, currNode.right);
        }
        else {
            return getHelper(key, currNode.left);
        }
    }

    /**
    * Returns the number of key-value mappings in this map.
    */
    public int size() {
        return size(root);
    }

    private int size(BSTNode n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    /**
    * Associates the specified value with the specified key in this map.
    */
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    private BSTNode putHelper(K key, V value, BSTNode n) {
        if (n == null) {
            return new BSTNode(key, value);
        }
        int compareInt = key.compareTo(n.key);
        if (compareInt == 0) {
            n.val = value;
        }
        else if (compareInt > 0) {
            n.right = putHelper(key, value, n.right);
        }
        else {
            n.left = putHelper(key, value, n.left);
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(BSTNode n) {
        // if node is null, nothing to print
        if (n == null) {
            System.out.println("Nothing to print!");
        }

        // if both left and right are null, only print the root node's key
        if (n.right == null && n.left == null) {
            System.out.println(n.key.toString());
        }

        // if left is null, print out the node's key first since it is smaller then the right tree.
        // then, print left tree
        else if (n.right != null && n.left == null) {
            System.out.println(n.key.toString());
            printInOrderHelper(n.right);
        }

        // if right is null, print out the left tree first since it is smaller
        // then, print the key
        else if (n.right == null && n.left != null) {
            printInOrderHelper(n.left);
            System.out.println(n.key.toString());
        }

        // otherwise, print left, root, right in that order.
        else {
            printInOrderHelper(n.left);
            System.out.println(n.key.toString());
            printInOrderHelper(n.right);
        }

    }



    public Set<K> keySet() {
        throw new UnsupportedOperationException("This operation does not exist!");
    }

    public V remove(K key) {
        throw new UnsupportedOperationException("This operation does not exist!");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This operation does not exist!");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("This operation does not exist!");
    }
}
