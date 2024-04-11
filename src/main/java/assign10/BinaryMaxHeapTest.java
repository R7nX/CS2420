package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest<E> {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSize() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));

		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
		assertEquals(7, actual.size());

	}

	@Test
	void testPeek() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));
		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers, (i1, i2) -> i2 - i1);
		assertEquals((Integer) (-8), actual.peek());
	}

	@Test
	void testAddBinaryMaxHeap() {
		List<Integer> numbersExpected = new ArrayList<Integer>(
				Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80, 10 }));
		BinaryMaxHeap<Integer> expected = new BinaryMaxHeap<Integer>(numbersExpected);

		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));
		numbers.add(10);
		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);

		// Convert heaps to arrays for comparison
		Object[] actualArray = actual.toArray();
		Object[] expectedArray = expected.toArray();

		// Sort arrays for comparison
		Arrays.sort(actualArray, Collections.reverseOrder());
		Arrays.sort(expectedArray, Collections.reverseOrder());

		// Assert arrays are equal
		assertArrayEquals(expectedArray, actualArray);

	}

	@Test
	void testExtractMax() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));

		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
		assertEquals(80, actual.extractMax());

	}

	@Test
	void testIsEmpty() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));

		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
		assertFalse(actual.isEmpty());
		actual.clear();
		assertTrue(actual.isEmpty());
	}

	@Test
	void testClear() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 45, -6, 78, 23, 0, -8, 80 }));

		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
		actual.clear();
		assertEquals(0, actual.size());
		assertTrue(actual.isEmpty());

		List<Integer> empty = new ArrayList<Integer>(Arrays.asList(new Integer[] {}));
		BinaryMaxHeap<Integer> actualEmpty = new BinaryMaxHeap<Integer>(empty);
		assertEquals(0, actualEmpty.size());
		assertTrue(actualEmpty.isEmpty());
	}
// BinaryMaxHeap 
	@Test
	void testBinaryMaxHeap() {
		 // Create an empty BinaryMaxHeap
	    BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();

	    // Add elements to the heap
	    heap.add(45);
	    heap.add(-6);
	    heap.add(78);
	    heap.add(23);
	    heap.add(0);
	    heap.add(-8);
	    heap.add(80);

	    // Convert the heap to an array for comparison
	    Object[] actualArray = heap.toArray();

	    // Create the expected array with the elements in the expected order
	    Object[] expectedArray = {80, 45, 78, 23, 0, -6, -8};

	    // Sort both arrays in reverse order for comparison
	    Arrays.sort(actualArray, Collections.reverseOrder());
	    Arrays.sort(expectedArray, Collections.reverseOrder());

	    // Assert arrays are equal
	    assertArrayEquals(expectedArray, actualArray);
	}
// BinaryMaxHeap Comparator
	@Test
	void testBinaryMaxHeapComparator() {
	  
	}
// BinaryMaxHeap List 
	@Test
	void testBinaryMaxHeapList() {

	}

	@Test
	void testBinaryMaxHeapListWithComparator() {

	}
}
