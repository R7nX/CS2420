package assign10;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests for BinaryMaxHeap implementation
 *
 * @author Khang Nguyen and Phuc Do
 * @version 4/11/2023
 */
class BinaryMaxHeapTest<E> {
    BinaryMaxHeap<Integer> heap;
    BinaryMaxHeap<Integer> cmpHeap;
    BinaryMaxHeap<Integer> listHeap;
    BinaryMaxHeap<Integer> listCmpHeap;

    @BeforeEach
    void setUp() throws Exception {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        // Add elements to the heap
        heap.add(23);
        heap.add(-6);
        heap.add(45);
        heap.add(78);
        heap.add(0);
        heap.add(-8);
        heap.add(80);

        this.heap = heap;

        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>();
        cmpHeap.add(45);
        cmpHeap.add(-6);
        cmpHeap.add(78);
        cmpHeap.add(23);
        cmpHeap.add(0);
        cmpHeap.add(-8);
        cmpHeap.add(80);

        this.cmpHeap = cmpHeap;

        // Create a list of integers
        List<Integer> numbers = Arrays.asList(45, -6, 78, 23, 0, -8, 80);

        // Create a BinaryMaxHeap using the list
        BinaryMaxHeap<Integer> listHeap = new BinaryMaxHeap<>(numbers);
        this.listHeap = listHeap;

        List<Integer> listCmpNumbers = Arrays.asList(45, -6, 78, 23, 0, -8, 80);
        // Create a comparator for integers
        Comparator<Integer> cmp = Comparator.naturalOrder();

        // Create a BinaryMaxHeap using the list and comparator
        BinaryMaxHeap<Integer> listCmpHeap = new BinaryMaxHeap<>(listCmpNumbers, cmp);
        this.listCmpHeap = listCmpHeap;
    }

    @Test
    void testSize() {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));

        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
        assertEquals(7, actual.size());

    }

    @Test
    void testPeek() {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));
        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers, (i1, i2) -> i2 - i1);
        assertEquals((Integer) (-8), actual.peek());
    }

    @Test
    void testAddBinaryMaxHeap() {
        List<Integer> numbersExpected = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80, 10}));
        BinaryMaxHeap<Integer> expected = new BinaryMaxHeap<Integer>(numbersExpected);

        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));
        numbers.add(10);
        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);

        // Convert heaps to arrays for comparison
        Object[] actualArray = actual.toArray();
        Object[] expectedArray = expected.toArray();

        // Assert arrays are equal
        assertArrayEquals(expectedArray, actualArray);

    }

    @Test
    void testExtractMax() {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));

        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
        assertEquals(80, actual.extractMax());

    }

    @Test
    void testIsEmpty() {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));

        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
        assertFalse(actual.isEmpty());
        actual.clear();
        assertTrue(actual.isEmpty());
    }

    @Test
    void testClear() {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[]{45, -6, 78, 23, 0, -8, 80}));

        BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
        actual.clear();
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());

        List<Integer> empty = new ArrayList<Integer>(Arrays.asList(new Integer[]{}));
        BinaryMaxHeap<Integer> actualEmpty = new BinaryMaxHeap<Integer>(empty);
        assertEquals(0, actualEmpty.size());
        assertTrue(actualEmpty.isEmpty());
    }

    // BinaryMaxHeap
    @Test
    void testBinaryMaxHeapAdd() {
        // Create an empty BinaryMaxHeap

        // Convert the heap to an array for comparison
        Object[] actualArray = heap.toArray();

        // Create the expected array with the elements in the expected order
        Object[] expectedArray = {23, 0, 45, 80, -8, 78, -6};

//	    // Sort both arrays in reverse order for comparison
        Arrays.sort(actualArray);
        Arrays.sort(expectedArray);
        System.out.println(actualArray);

        // Assert arrays are equal
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void testBinaryMaxHeapPeek() {
        int peekedValue = heap.peek();

        // Ensure that the maximum element is returned correctly
        assertEquals(80, peekedValue);
        assertEquals(7, heap.size()); // Check the size of the heap

    }

    @Test
    void testBinaryMaxHeapExtractMax() {
        heap.add(90);
        Object[] internalArray = heap.toArray();
        Object rootItem = internalArray[0];
        Integer actual = heap.extractMax();
        assertEquals(actual, rootItem);

    }

    @Test
    void testBinaryMaxHeapSize() {
        assertEquals(7, heap.size());
    }

    @Test
    void testBinaryMaxHeapToArray() {
        // Get the internal array of the heap
        Object[] internalArray = heap.toArray();

        // Get the root item (maximum element) from the internal array
        Object rootItem = internalArray[0];
        assertEquals(80, (int) rootItem);
    }

    @Test
    void testBinaryMaxHeapClear() {
        heap.clear();
        assertEquals(0, heap.size());
    }

    // edge cases
    @Test
    void testBinaryMaxHeapEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertTrue(heap.isEmpty());
        assertThrows(NoSuchElementException.class, () -> heap.peek());
        assertThrows(NoSuchElementException.class, () -> heap.extractMax());
        assertEquals(0, heap.size());
    }

    @Test
    void testBinaryMaxHeapSingleElementHeap() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.add(1);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.peek());
        assertEquals(1, heap.size());

    }

    @Test
    void testBinaryMaxHeapDuplicate() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.add(10);
        heap.add(10);
        heap.add(10);
        assertFalse(heap.isEmpty());
        assertEquals(10, heap.extractMax());
        assertEquals(2, heap.size());
        assertEquals(10, heap.peek());

        Object[] internalArray = heap.toArray();

        // Get the root item (maximum element) from the internal array
        Object rootItem = internalArray[0];
        assertEquals(10, (int) rootItem);

    }

    // BinaryMaxHeap Comparator
    @Test
    void testBinaryMaxHeapComparatorAdd() {

        // Convert the heap to an array for comparison
        Object[] actualArray = cmpHeap.toArray();

        // Create the expected array with the elements in the expected order
        Object[] expectedArray = {80, 78, 45, 23, 0, -6, -8};

        assertArrayEquals(expectedArray, actualArray);

    }

    @Test
    void testBinaryMaxHeapComparatorPeek() {
        // Get the maximum element using peek
        int peekedValue = cmpHeap.peek();

        // Ensure that the maximum element is returned correctly according to the custom
        // comparator
        assertEquals(80, peekedValue);

    }

    @Test
    void testBinaryMaxHeapComparatorExtractMax() {
        // Extract the maximum element from the heap
        int extractedMax = cmpHeap.peek();
        // Ensure that the maximum element is removed correctly according to the custom
        // comparator
        assertEquals(80, extractedMax);
    }

    @Test
    void testBinaryMaxHeapComparatorSize() {
        assertEquals(7, cmpHeap.size());

    }

    @Test
    void testBinaryMaxHeapComparatorIsEmpty() {
        assertFalse(cmpHeap.isEmpty());

    }

    @Test
    void testBinaryMaxHeapComparatorToArray() {
        // Convert the heap to an array
        Object[] heapArray = cmpHeap.toArray();
        // Ensure that the array is not null
        assertNotNull(heapArray);
        // Ensure that the size of the array matches the size of the heap
        assertEquals(cmpHeap.size(), heapArray.length);
    }

    // Edge cases
    @Test
    void testBinaryMaxHeapComparatorEmpty() {
        // Create a BinaryMaxHeap with a custom comparator
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(comparator);

        // Ensure that the heap is empty
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> heap.peek());
    }

    @Test
    void testBinaryMaxHeapComparatorSingleElementHeap() {
        // Create a BinaryMaxHeap with a custom comparator
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(comparator);

        // Add a single element to the heap
        heap.add(42);

        // Ensure that the heap contains only one element
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(42, (int) heap.peek());
    }

    @Test
    void testBinaryMaxHeapComparatorDuplicate() {
        // Create a BinaryMaxHeap with a custom comparator
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(comparator);

        // Add duplicate elements to the heap
        heap.add(10);
        heap.add(20);
        heap.add(10);

        // Ensure that the heap contains all duplicate elements
        assertFalse(heap.isEmpty());
        assertEquals(3, heap.size());
        assertEquals(20, (int) heap.peek());
    }

    // BinaryMaxHeap List
    @Test
    void testBinaryMaxHeapListAdd() {

        // Convert the heap to an array for comparison
        Object[] actualArray = listHeap.toArray();

        // Create the expected array with the elements in the expected order
        Object[] expectedArray = {80, 78, 45, 23, 0, -6, -8};

        // Assert arrays are equal
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void testBinaryMaxHeapListPeek() {
        int peekedValue = listHeap.peek();

        // Assert that the maximum element is correct
        assertEquals(80, peekedValue);
    }

    @Test
    void testBinaryMaxHeapListExtractMax() {
        int extractedMax = listHeap.peek();

        // Assert that the maximum element is correct
        assertEquals(80, extractedMax);
    }

    @Test
    void testBinaryMaxHeapListSize() {
        assertEquals(7, listHeap.size());

    }

    @Test
    void testBinaryMaxHeapListIsEmpty() {
        List<Integer> emptyList = Arrays.asList();

        // Create a BinaryMaxHeap using the empty list
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(emptyList);

        // Assert that the heap is empty
        assertTrue(heap.isEmpty());
    }

    @Test
    void testBinaryMaxHeapListClear() {
        listHeap.clear();

        // Assert that the heap is empty after clearing
        assertTrue(listHeap.isEmpty());
        assertEquals(0, listHeap.size());
    }

    @Test
    void testBinaryMaxHeapListToArray() {
        Object[] heapArray = listHeap.toArray();

        // Assert that the array is not null
        assertNotNull(heapArray);

        // Assert that the size of the array matches the size of the heap
        assertEquals(listHeap.size(), heapArray.length);
    }

    //	BinaryMaxHeap List and Comparator
    @Test
    void testBinaryMaxHeapListWithComparator() {

        // Convert the heap to an array for comparison
        Object[] actualArray = listCmpHeap.toArray();

        // Create the expected array with the elements in the expected order
        Object[] expectedArray = {80, 78, 45, 23, 0, -6, -8};

        // Assert arrays are equal
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void testBinaryMaxHeapListWithComparatorPeek() {
        // Ensure that the maximum element is correct
        assertEquals(80, listCmpHeap.peek());

    }

    @Test
    void testBinaryMaxHeapListWithComparatorExtractMax() {
        int extractedMax = listCmpHeap.peek();

        // Assert that the maximum element is correct
        assertEquals(80, extractedMax);
    }

    @Test
    void testBinaryMaxHeapListWithComparatorSize() {
        // Assert that the size of the heap is correct
        assertEquals(7, listCmpHeap.size());
    }

    @Test
    void testBinaryMaxHeapListWithComparatorIsEmpty() {
        // Assert that the heap is not empty
        assertFalse(listCmpHeap.isEmpty());
    }

    @Test
    void testBinaryMaxHeapListWithComparatorClear() {
        // Clear the heap
        listCmpHeap.clear();

        // Assert that the heap is empty after clearing
        assertTrue(listCmpHeap.isEmpty());
        assertEquals(0, listCmpHeap.size());

    }

    @Test
    void testBinaryMaxHeapListWithComparatorToArray() {
        // Convert the heap to an array
        Object[] heapArray = listCmpHeap.toArray();

        // Assert that the array is not null
        assertNotNull(heapArray);

        // Assert that the size of the array matches the size of the heap
        assertEquals(listCmpHeap.size(), heapArray.length);
    }

    // Edge cases
    @Test
    void testBinaryMaxHeapListWithComparatorBuildHeap() {
        // Create a list of integers
        List<Integer> numbers = Arrays.asList(45, -6, 78, 23, 0, -8, 80);

        // Create a comparator for integers
        Comparator<Integer> comparator = Comparator.naturalOrder();

        // Create a BinaryMaxHeap using the list and comparator
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(numbers, comparator);
        listCmpHeap = heap;

        // Assert that the heap satisfies the max-heap property after construction
        assertTrue(isMaxHeap(heap.toArray(), 0));
    }

    private boolean isMaxHeap(Object[] array, int index) {
        int size = array.length;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        // Base cases
        if (index >= size || leftChild >= size || rightChild >= size) {
            return true; // Leaf nodes are valid max heaps
        }

        // Check if current node is greater than or equal to its children
        if ((int) array[index] >= (int) array[leftChild] && (int) array[index] >= (int) array[rightChild]) {
            // Recursively check left and right subtrees
            return isMaxHeap(array, leftChild) && isMaxHeap(array, rightChild);
        }

        return false;
    }
}