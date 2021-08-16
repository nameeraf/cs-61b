package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Constructor Method. */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        // item is added to nextFirst, and nextFirst is moved to the "previous" index.
        items[nextFirst] = item;
        nextFirst = reduceIndex(nextFirst);
        size += 1;

    }

    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        // item is added to nextLast, and nextLast is moved to the "next" index.
        items[nextLast] = item;
        nextLast = increaseIndex(nextLast);
        size += 1;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints all the items in the deque from first to last. */
    public void printDeque() {
        for (int i = 0; i <= (size - 1); i++) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front. If none, return null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        // nextFirst must now be where the first element will be removed from.
        nextFirst = increaseIndex(nextFirst);

        // Nullifying the first value.
        T firstVal = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        // For arrays with length >= 16, usage factor should be at least 25%.
        double usageFactor = (double) size / items.length;
        if (items.length >= 16 && usageFactor < 0.25) {
            resize(items.length / 2);
        }

        return firstVal;
    }

    /**  Removes and returns the item at the back. If none, return null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        // nextLast must now be where the last element will be removed from.
        nextLast = reduceIndex(nextLast);

        // Nullifying the last value.
        T lastVal = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        // For arrays with length >= 16, usage factor should be at least 25%.
        double usageFactor = (double) size / items.length;
        if (items.length >= 16 && usageFactor < 0.25) {
            resize(items.length / 2);
        }

        return lastVal;
    }

    /** Reduces index i, making sure i is never negative or out of bounds. */
    private int reduceIndex(int i) {
        return (i - 1 + items.length) % items.length;
    }

    /** Increases index i, making sure i is never out of bounds. */
    private int increaseIndex(int i) {
        return (i + 1) % items.length;
    }

    /** Creates a new AList and assigns it to items, which can hold capacity items. */
    private void resize(int newLength) {

        T[] biggerItems = (T[]) new Object[newLength];

        // Copy all items from the old array into the new one.
        // Current first item is increaseIndex(nextFirst)

        for (int i = 0; i < size; i++) {
            biggerItems[i] = get(i);
        }
        items = biggerItems;
        nextFirst = newLength - 1;
        nextLast = size;

    }


    /**  Gets the item at the given index. If no such item exists, returns null. */
    public T get(int index) {
        // If the index is out of bounds, return null.
        if (index >= size) {
            return null;
        }

        // Transform user's index into index in the underlying array.
        // The first element occurs at the "next" index after nextFirst.
        int trueIndex = (increaseIndex(nextFirst) + index) % items.length;
        return items[trueIndex];
    }

    /** Returns an iterator. */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        ArrayDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = get(position);
            position += 1;
            return returnItem;
        }
    }

    /** Returns whether or not o has the same contents in the same order as the Deque. */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> d = (Deque<T>) o;
        if (d.size() != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!((this.get(i)).equals(d.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
