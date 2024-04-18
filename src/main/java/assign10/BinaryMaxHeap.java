package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 *
 * @author Phuc Do and Khang Nguyen
 * @version 4/11/2023
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    private E[] heap;
    private int size;
    private Comparator<? super E> comparator;

    /**
     * Adds an element to the heap.
     *
     * @param item the item to add
     */
    @Override
    public void add(E item) {
        if (size == heap.length)
            resizeHeap();
        heap[size] = item;
        percolateUp(size);
        size++;
    }

    /**
     * Retrieves the maximum element from the heap without removing it.
     *
     * @return the maximum element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty");

        return heap[0];
    }

    /**
     * Extracts and removes the maximum element from the heap.
     *
     * @return the maximum element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        E max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        percolateDown(0);
        return max;
    }

    /**
     * Returns the size of the heap.
     *
     * @return the number of elements in the heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap, removing all elements.
     */
    @Override
    public void clear() {
        this.heap = (E[]) new Object[16];
        this.size = 0;
    }

    /**
     * Converts the heap to an array.
     *
     * @return an array containing all elements in the heap
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        BinaryMaxHeap<E> temp = new BinaryMaxHeap<>(Arrays.asList(heap));
        for (int i = 0; i < size; i++) {
            array[i] = temp.extractMax();
        }
        return array;
    }

    /**
     * Constructs an empty BinaryMaxHeap.
     */
    public BinaryMaxHeap() {
        heap = (E[]) new Object[16];
        size = 0;
        comparator = null;
    }

    /**
     * Constructs an empty BinaryMaxHeap with the specified comparator.
     *
     * @param cmp the comparator to be used for ordering elements
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        heap = (E[]) new Object[16];
        size = 0;
        comparator = cmp;
    }

    /**
     * Constructs a BinaryMaxHeap containing the elements of the specified list.
     *
     * @param list the list whose elements are to be placed into this heap
     */
    public BinaryMaxHeap(List<? extends E> list) {
        heap = (E[]) new Object[list.size()];
        size = list.size();
        for (int i = 0; i < size; i++) {
            heap[i] = list.get(i);
        }
        buildHeap();
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the
     * given list (see RECALL note above). Also, it is assumed that the elements are
     * ordered using the provided Comparator object.
     *
     * @param list the list whose elements are to be placed into this heap
     * @param cmp  the comparator to be used for ordering elements
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        heap = (E[]) new Object[list.size()];
        size = list.size();
        comparator = cmp;
        for (int i = 0; i < size; i++) {
            heap[i] = list.get(i);
        }
        buildHeap();
    }

    /**
     * Builds the heap from the current elements in the heap array.
     * This method is called during heap construction and ensures that the heap property is maintained.
     */
    private void buildHeap() {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Performs the percolate-up operation starting from the specified index.
     * This operation moves an element up the heap to maintain the heap property.
     *
     * @param index the index from which to start the percolate-up operation
     */
    @SuppressWarnings("unchecked")
    private void percolateUp(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && innerCompare(heap[index], heap[parentIndex]) > 0) {
            E temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;

            index = parentIndex;

            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * Performs the percolate-down operation starting from the specified index.
     * This operation moves an element down the heap to maintain the heap property.
     *
     * @param index the index from which to start the percolate-down operation
     */
    @SuppressWarnings("unchecked")
    private void percolateDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && innerCompare(heap[leftChild], heap[largest]) > 0)
            largest = leftChild;

        if (rightChild < size && innerCompare(heap[rightChild], heap[largest]) > 0)
            largest = rightChild;

        if (largest != index) {
            E temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;
            percolateDown(largest);
        }
    }

    /**
     * Resizes the heap array when it becomes full.
     * The new capacity of the heap array is twice the current capacity.
     */
    private void resizeHeap() {
        int newCapacity = heap.length * 2;
        E[] newHeap = (E[]) new Object[newCapacity];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    /**
     * Compares two objects using either the provided comparator or their natural ordering.
     *
     * @param obj1 the first object to compare
     * @param obj2 the second object to compare
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to,
     *         or greater than the second, respectively
     */
    private int innerCompare(E obj1, E obj2) {
        if (obj1 == null && obj2 == null)
            return 0;
        else if (obj1 == null)
            return -1;
        else if (obj2 == null)
            return 1;
        else if (comparator != null)
            return comparator.compare(obj1, obj2);
        else
            return ((Comparable<? super E>) obj1).compareTo(obj2);
    }
}
