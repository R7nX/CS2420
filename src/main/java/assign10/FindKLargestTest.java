package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests for FindKLargest implementation
 *
 * @author Khang Nguyen and Phuc Do
 * @version 4/11/2023
 */
class FindKLargestTest {

	@BeforeEach
	void setUp() throws Exception {
	}
// using Java's sort routine and the natural ordering of the items extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k)

	@Test
	public void testFindKLargestHeap() {
		List<Integer> items = Arrays.asList(1, 5, 2, 8, 3, 9, 4, 7, 6);
		int k = 3;
		List<Integer> expected = Arrays.asList(9, 8, 7);

		assertArrayEquals(FindKLargest.findKLargestHeap(items, k).toArray(), expected.toArray());
	}

	@Test
	public void testFindKLargestHeap_EmptyList() {
		List<Integer> items = Collections.emptyList();
		int k = 3;

		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(items, k));
	}

	@Test
	public void testFindKLargestHeap_KOutOfRange() {
		List<Integer> items = Arrays.asList(1, 2, 3);
		int k = 5;
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(items, k));
	}

// 	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp)

	@Test
	public void testFindKLargestHeapWithComparator() {
		List<Integer> items = Arrays.asList(1, 5, 2, 8, 3, 9, 4, 7, 6);
		int k = 3;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertArrayEquals(FindKLargest.findKLargestHeap(items, k, cmp).toArray(), expected.toArray());
	}

	@Test
	public void testFindKLargestHeapWithComparator_EmptyList() {
		List<Integer> items = Arrays.asList();
		int k = 3;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(items, k, cmp));
	}

	@Test
	public void testFindKLargestHeapWithComparator_KOutOfRange() {
		List<Integer> items = Arrays.asList(1, 2, 3);
		int k = 5;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(items, k, cmp));
	}

// 	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k)
	@Test
	public void testFindKLargestSort() {
		List<Integer> items = Arrays.asList(1, 5, 2, 8, 3, 9, 4, 7, 6);
		int k = 3;
		List<Integer> expected = Arrays.asList(9, 8, 7);

		assertArrayEquals(FindKLargest.findKLargestSort(items, k).toArray(), expected.toArray());
	}
	
	@Test
	public void testFindKLargestSort_EmptyList() {
		List<Integer> items = Collections.emptyList();
		int k = 3;

		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(items, k));
	}

	@Test
	public void testFindKLargestSort_KOutOfRange() {
		List<Integer> items = Arrays.asList(1, 2, 3);
		int k = 5;
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(items, k));
	}

// 	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp)
	@Test
	public void testFindKLargestSortWithComparator() {
		List<Integer> items = Arrays.asList(1, 5, 2, 8, 3, 9, 4, 7, 6);
		int k = 3;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertArrayEquals(FindKLargest.findKLargestSort(items, k, cmp).toArray(), expected.toArray());
	}

	@Test
	public void testFindKLargesSortWithComparator_EmptyList() {
		List<Integer> items = Arrays.asList();
		int k = 3;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(items, k, cmp));
	}

	@Test
	public void testFindKLargestSortWithComparator_KOutOfRange() {
		List<Integer> items = Arrays.asList(1, 2, 3);
		int k = 5;
		Comparator<Integer> cmp = Comparator.reverseOrder(); // Reverse order comparator
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(items, k, cmp));
	}
	
	
}
