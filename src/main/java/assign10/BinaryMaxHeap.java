package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    private E[] heap;
    private int size;
    private Comparator<? super E> comparator;

    /**
     * Step 1) Add the item in the next available space. (satisfy structure
     * property),
     * <p>
     * Step 2) Move it up the tree until the order is correct. (satisfy
     * heap order property) (Called percolate-up, bubble-up, sift-up, etc.)
     */
    @Override
    public void add(E item) {
        if (size == heap.length)
            throw new IllegalArgumentException();
        heap[size] = item;
        percolateUp(size - 1);
        size++;

    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty");

        return heap[0];
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.heap = null;
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = heap[i];
        }
        return array;
    }

    /**
     * If this constructor is used to create an empty binary heap, it is assumed
     * that the elements are ordered using their natural ordering (i.e., E
     * implements Comparable<? super E>).
     */
    public BinaryMaxHeap() {
        heap = (E[]) new Object[16];
        size = 0;
        comparator = null;
    }

    /**
     * If this constructor is used to create an empty binary heap, it is assumed
     * that the elements are ordered using the provided Comparator object.
     *
     * @param cmp
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        heap = (E[]) new Object[16];
        size = 0;
        comparator = cmp;
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the
     * given list. Also, it is assumed that the elements are ordered using their
     * natural ordering (i.e., E implements Comparable<? super E>).
     * <p>
     * RECALL: Using the buildHeap operation, we can construct a binary heap from
     * these N items in O(N) time, which is more efficient than adding them to the
     * binary heap one at a time. This constructor must use such an operation.
     *
     * @param list
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
     * @param list
     * @param cmp
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
// private helper method buildHeap, percolateUp, percolateDown, innerCompare

    private void buildHeap() {
        //This is kinda sus, check it thoroughly or u cooked
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    @SuppressWarnings("unchecked")
    private void percolateUp(int index) {
        // Get the parent index
        int parentIndex = (index - 1) / 2;

        // While the current element is greater than its parent
        while (index > 0 && innerCompare(heap[index], heap[parentIndex]) > 0) {
            // Swap the current element with its parent
            E temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;

            // Update the current index to the parent index
            index = parentIndex;

            // Get the new parent index
            parentIndex = (index - 1) / 2;
        }
    }

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

    private int innerCompare(E obj1, E obj2) {
        if (obj1 == null && obj2 == null)
            return 0;
        else if (obj1 == null)
            return -1; // null is considered less than any non-null value
        else if (obj2 == null)
            return 1; // any non-null value is considered greater than null
        else if (comparator != null)
            return comparator.compare(obj1, obj2);
        else
            return ((Comparable<? super E>) obj1).compareTo(obj2);

    }

}
