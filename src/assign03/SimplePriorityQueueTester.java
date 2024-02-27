package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for SimplePriorityQueue.
 *
 * @author Phuc Do and Khang Nguyen
 * @version Feb 01, 2024
 */
class SimplePriorityQueueTester<E> {

    private SimplePriorityQueue<Integer> priorityQueue;

    @BeforeEach
    void setUp() throws Exception {
        priorityQueue = new SimplePriorityQueue<>();
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        priorityQueue.insert(numbers.get(1));
        priorityQueue.insert(numbers.get(9));
        priorityQueue.insert(numbers.get(4));
        priorityQueue.insert(numbers.get(3));
        priorityQueue.insert(numbers.get(7));
        priorityQueue.insert(numbers.get(2));

    }

    @Test
    void testFindMax() {

        assertEquals(9, priorityQueue.findMax());

    }

    @Test
    void testFindMaxThrow() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();
        assertThrows(NoSuchElementException.class, () -> {
            priorityQueueArr.findMax();
        });
    }

    @Test
    void testdeleteMax() {
        priorityQueue.deleteMax();
        assertEquals(7, priorityQueue.findMax());

    }

    @Test
    void testDeleteMaxEmpty() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();
        assertThrows(NoSuchElementException.class, () -> {
            priorityQueueArr.deleteMax();
        });
    }

    @Test
    void testInsert() {
        priorityQueue.insert(8);
        assertTrue(priorityQueue.contains(8));

    }

    @Test
    void testInsertNull() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        priorityQueueArr.insert(null);
        assertNull(priorityQueueArr.findMax());

    }

    @Test
    void testInsertDuplication() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        priorityQueueArr.insert(5);
        priorityQueueArr.insert(10);
        priorityQueueArr.insert(5);

        assertEquals(10, priorityQueueArr.findMax());
        assertEquals(3, priorityQueueArr.size());
    }

    @Test
    void testInsertAndDelete() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        priorityQueueArr.insert(5);
        priorityQueueArr.insert(10);
        priorityQueueArr.insert(9);

        priorityQueueArr.deleteMax();
        assertEquals(9, priorityQueueArr.findMax());

        priorityQueueArr.deleteMax();
        assertEquals(5, priorityQueueArr.findMax());

        priorityQueueArr.deleteMax();
        assertTrue(priorityQueueArr.isEmpty());
        assertEquals(0, priorityQueueArr.size());

    }

    @Test
    void testInsertNegativenumber() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        priorityQueueArr.insert(-5);
        priorityQueueArr.insert(-10);
        priorityQueueArr.insert(-9);

        assertEquals(-5, priorityQueueArr.findMax());

    }

    @Test
    void testInsertDoubleArr() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        for (int i = 0; i <= 20; i++) {
            priorityQueueArr.insert(i);
        }
        assertTrue(priorityQueueArr.contains(20), "PriorityQueue should not be empty after clearing");

    }

    @Test
    void testInsertAll() {
        ArrayList<Integer> numbers = new ArrayList<>();
        SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        priorityQueue.insert(numbers.get(1));
        priorityQueue.insert(numbers.get(9));
        priorityQueue.insert(numbers.get(4));
        priorityQueue.insert(numbers.get(3));
        priorityQueue.insert(numbers.get(7));

        priorityQueue.insertAll(numbers);

        assertTrue(priorityQueue.contains(9));
    }

    @Test
    void testInsertAllEmptyCollection() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        ArrayList<Integer> emptyList = new ArrayList<>();
        priorityQueueArr.insertAll(emptyList);

        assertTrue(priorityQueueArr.isEmpty());
        assertEquals(0, priorityQueueArr.size());
    }

    @Test
    void testSize() {
        assertEquals(6, priorityQueue.size());

    }

    @Test
    void testSizeShrink() {
        priorityQueue.deleteMax();
        assertEquals(5, priorityQueue.size());

    }

    @Test
    void testClear() {

        priorityQueue.clear();
        assertFalse(priorityQueue.contains(9));

    }

    @Test
    void testInsertClear() {
        SimplePriorityQueue<Integer> priorityQueueArr = new SimplePriorityQueue<>();

        priorityQueueArr.insert(5);
        priorityQueueArr.insert(10);
        priorityQueueArr.insert(9);
        priorityQueueArr.clear();
        assertEquals(0, priorityQueueArr.size());

    }

    @Test
    void testIsEmpty() {
        priorityQueue.clear();
        assertTrue(priorityQueue.isEmpty(), "PriorityQueue should be empty after clearing");
    }

    @Test
    void testIsNotEmpty() {
        assertFalse(priorityQueue.isEmpty(), "PriorityQueue should not be empty after clearing");

    }

    @Test
    void testContain() {
        assertTrue(priorityQueue.contains(2));
    }

    @Test
    void testNotContain() {
        assertFalse(priorityQueue.contains(18));
    }


}
