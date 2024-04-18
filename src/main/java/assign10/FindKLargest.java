package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker and Phuc Do and Khang Nguyen
 * @version 4/11/2023
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		List<E> result = new ArrayList<>();
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<>(items);
		for (int i = 0; i < k; i++)
			result.add(maxHeap.extractMax());
		return result;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		List<E> result = new ArrayList<>();
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<>(items, cmp);
		for (int i = 0; i < k; i++)
			result.add(maxHeap.extractMax());
		return result;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		Collections.sort(items, Collections.reverseOrder());
		List<E> kLargestItems = new ArrayList<>(k);

		for (int i = 0; i < k; i++) {
			kLargestItems.add(items.get(i));
		}

		return kLargestItems;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();

		Collections.sort(items, cmp.reversed());

		// Create a new list to store the k largest items
		List<E> kLargestItems = new ArrayList<>(k);

		// Add the first k items to the new list
		for (int i = 0; i < k; i++) {
			kLargestItems.add(items.get(i));
		}

		return kLargestItems;
	}
}