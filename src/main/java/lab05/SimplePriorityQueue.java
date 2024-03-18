package lab05;

import assign03.PriorityQueue;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a simple implementation of a priority queue.
 *
 * @param <E> the type of elements in the priority queue
 * @author Phuc Do and Khang Nguyen
 * @version Feb 01, 2023
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterable<E> {
    private E[] backingArr;
    private int length;
    private Comparator<? super E> comparator;
    private int size;

    /**
     * Constructs an empty priority queue with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        this.length = 16;
        backingArr = (E[]) new Object[length];
        this.size = 0;
        this.comparator = null;
    }

    /**
     * Constructs an empty priority queue with an initial capacity of 16 and the
     * specified comparator.
     *
     * @param cmp the comparator to determine the ordering of elements
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        this.length = 16;
        backingArr = (E[]) new Object[length];
        this.size = 0;
        this.comparator = cmp;
    }

    /**
     * Retrieves the maximum element in the priority queue.
     *
     * @return the maximum element in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("The priority queue is empty");
        return (E) backingArr[size - 1];
    }

    /**
     * Deletes and returns the maximum element in the priority queue.
     *
     * @return the maximum element in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("The priority queue is empty");
        E max = backingArr[size - 1];
        backingArr[size - 1] = null;
        this.size--;
        return max;
    }

    /**
     * Inserts the specified element into the priority queue.
     *
     * @param item the element to insert
     */
    @Override
    public void insert(E item) {
        if (this.size == backingArr.length)
            doubleBackingArray();

        int index = binarySearch(item);

        if (index < size) {
            for (int i = size; i > index; i--) {
                backingArr[i] = backingArr[i - 1];
            }
        }
        backingArr[index] = item;
        size++;
    }

    /**
     * Inserts all elements from the specified collection into the priority queue.
     *
     * @param coll the collection containing elements to be inserted
     */
    @Override
    public void insertAll(Collection<? extends E> coll) {
        for (E item : coll) {
            insert(item);
        }
    }

    /**
     * Checks if the priority queue contains the specified element.
     *
     * @param item the element to check for
     * @return true if the priority queue contains the element, false otherwise
     */
    @Override
    public boolean contains(E item) {
        int index = binarySearch(item);

        return index < size && compare((E) item, (E) backingArr[index]) == 0;
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all elements from the priority queue.
     */
    @Override
    public void clear() {
        for (int i = 0; i < backingArr.length; i++)
            backingArr[i] = null;
        this.size = 0;
    }

    // Private helper methods

    /**
     * Compares two elements for ordering. If a comparator is specified during the
     * construction, it is used; otherwise, elements are compared based on their
     * natural ordering.
     *
     * @param e1 the first element to be compared
     * @param e2 the second element to be compared
     * @return a negative integer, zero, or a positive integer if the first element
     * is less than, equal to, or greater than the second
     */
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        if (this.comparator == null)
            return ((Comparable<? super E>) e1).compareTo(e2);
        else
            return this.comparator.compare(e1, e2);
    }

    /**
     * Doubles the capacity of the backing array when the current capacity is
     * reached.
     */
    @SuppressWarnings("unchecked")
    private void doubleBackingArray() {
        this.length = length * 2;
        E[] largerArr = (E[]) new Object[length];
        for (int i = 0; i < backingArr.length; i++) {
            largerArr[i] = backingArr[i];
        }
        backingArr = largerArr;
    }

    /**
     * Performs a binary search to find the index where the specified element should
     * be inserted.
     *
     * @param element the element to be inserted or searched for
     * @return the index at which the element should be inserted
     */
    private int binarySearch(E element) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = compare(element, backingArr[mid]);

            if (cmp > 0)
                low = mid + 1;
            else if (cmp < 0)
                high = mid - 1;
            else
                return mid;
        }
        return low;
    }

    @Override
    public Iterator<E> iterator() {
        return new PriorityQueueIterator();
    }

    private class PriorityQueueIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return backingArr[currentIndex++];
        }

        @Override
        public void remove() {
            SimplePriorityQueue.this.deleteMax(); // Assuming you want to remove the max element
            currentIndex--;
        }
    }
}