package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest<E> {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSize() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] {45, -6, 78, 23, 0, -8, 80}));
		
		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers);
		assertEquals(7, actual.size());
		
	}

	@Test
	void testPeek() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(new Integer[] {45, -6, 78, 23, 0, -8, 80}));
		BinaryMaxHeap<Integer> actual = new BinaryMaxHeap<Integer>(numbers, (i1, i2) -> i2 - i1);
		assertEquals((Integer)(-8), actual.peek());
		}
}
