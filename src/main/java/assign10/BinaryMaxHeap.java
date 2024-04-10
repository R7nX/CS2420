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
	 *  
	 * Step 2) Move it up the tree until the order is correct. (satisfy
	 * heap order property) (Called percolate-up, bubble-up, sift-up, etc.)
	 * 
	 */
	@Override
	public void add(E item) {
		// TODO Auto-generated method stub

	}

	@Override
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new NoSuchElementException("Heap is empty");

		return heap[0];
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return heap == null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.heap = null;
		this.size = 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using their natural ordering (i.e., E
	 * implements Comparable<? super E>).
	 */
	public BinaryMaxHeap() {

	}

	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using the provided Comparator object.
	 * 
	 * @param cmp
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {

	}

	/**
	 * If this constructor is used, then the binary heap is constructed from the
	 * given list. Also, it is assumed that the elements are ordered using their
	 * natural ordering (i.e., E implements Comparable<? super E>).
	 * 
	 * RECALL: Using the buildHeap operation, we can construct a binary heap from
	 * these N items in O(N) time, which is more efficient than adding them to the
	 * binary heap one at a time. This constructor must use such an operation.
	 * 
	 * 
	 * @param list
	 */
	public BinaryMaxHeap(List<? extends E> list) {

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

	}
// private helper method buildHeap, percolateUp, percolateDown, innerCompare

	private void buildHeap(int array[], int index) {
		for(int i = (array.length-1)/2; i > 0; i--)
			percolateDown(array, i, array.length);
		}

	private void percolateUp(int[] array, int index) {
		// Get the parent index
		int parentIndex = (index - 1) / 2;

		// While the current element is greater than its parent
		while (index > 0 && array[index] > array[parentIndex]) {
			// Swap the current element with its parent
			int temp = array[index];
			array[index] = array[parentIndex];
			array[parentIndex] = temp;

			// Update the current index to the parent index
			index = parentIndex;

			// Get the new parent index
			parentIndex = (index - 1) / 2;
		}
	}

	private void percolateDown(int[] array,int index , int length) {
		int leftChild = 2 * index;
		int rightChild = 2 * index + 1;

	    int largest = index;

		if (leftChild < array.length && array[leftChild] > array[index]) 
	        largest = leftChild;

		

		if (rightChild < array.length && array[rightChild] > array[index]) 
	        largest = rightChild;

		  if (largest != index) {
		        int temp = array[index];
		        array[index] = array[largest];
		        array[largest] = temp;
		        percolateDown(array, largest, length);
		    }
	}

	private void innerCompare(Object obj1, Object obj2) {

	}

}
