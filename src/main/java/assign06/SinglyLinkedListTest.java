package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
	private SinglyLinkedList<URL> list;

	@BeforeEach
	void setUp() throws Exception {
		SinglyLinkedList<URL> list = new SinglyLinkedList<URL>();
		this.list = list;
	}

// Test LinkedListStack Normal Cases
	@Test
	void testSinglyLinkedListInsertFirst() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		// Add assertions to verify the insertion
		assertEquals(url2, list.getFirst());
		assertEquals(url1, list.get(1));
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListInsert() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");

		list.insertFirst(url1); // Insert at the beginning
		list.insert(list.size(), url2); // Insert at the end
		list.insert(1, new URL("https://example3.com")); // Insert in the middle
		assertEquals(new URL("https://example3.com"), list.get(1));
		assertEquals(3, list.size());

	}

	@Test
	void testSinglyLinkedListInsertThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();

		// Test inserting at a negative index
		assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, new URL("https://example.com")));
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.insert(list.size() + 1, new URL("https://example.com")));

	}

	@Test
	void testSinglyLinkedListInsertIndexZero() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		URL url = new URL("https://example.com");

		// Insert an element at index 0
		list.insert(0, url);

		// Verify that the element is inserted at the beginning of the list
		assertEquals(url, list.getFirst());
	}

	@Test
	void testSinglyListStackGetFirstThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		assertThrows(NoSuchElementException.class, () -> list.getFirst());

	}

	@Test
	void testSinglyListStackGetThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size() + 1));

	}

	@Test
	void testSinglyLinkedListDeleteFirst() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insertFirst(url4);

		assertEquals(url4, list.deleteFirst()); // Verify the first element is removed and returned
		assertEquals(3, list.size());
		assertEquals(url3, list.deleteFirst()); // Verify the next element is removed and returned
		assertEquals(2, list.size());

	}

	@Test
	void testSinglyLinkedListDeleteFirstThrow() throws MalformedURLException {
		assertThrows(NoSuchElementException.class, () -> list.deleteFirst());

	}

	@Test
	void testSinglyLinkedListDelete() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insertFirst(url4);

		assertEquals(url1, list.delete(list.size() - 1));
		assertEquals(3, list.size());
		assertEquals(url2, list.delete(list.size() - 1));

	}

	@Test
	void testSinglyLinkedListDeleteThrow() throws MalformedURLException {
		assertThrows(IndexOutOfBoundsException.class, () -> list.delete(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.delete(list.size() + 1));

	}

	@Test
	void testSinglyLinkedListDeleteIndexZero() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);
		assertEquals(url1, list.delete(0));

	}

	@Test
	void testSinglyLinkedListIndexOf() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		list.insert(0, url1);
		list.insertFirst(url1);
		URL url2 = new URL("https://example2.com");
		list.insertFirst(url2);

		URL url3 = new URL("https://example3.com");
		list.insertFirst(url3);

		URL url4 = new URL("https://example4.com");
		list.insert(1, url4);

		assertEquals(0, list.indexOf(url3)); // Verify the index of the first element
		assertEquals(1, list.indexOf(url4)); // Verify the index of the second element
		assertEquals(2, list.indexOf(url2)); // Verify the index of the third element

	}

	@Test
	void testSinglyLinkedListIndexOfReturnNegativeOne() throws MalformedURLException {
		URL nonExistingURL = new URL("https://example3.com");
		int index = list.indexOf(nonExistingURL);

		// Verify that -1 is returned since the element is not in the list
		assertEquals(-1, index);
	}

	@Test
	void testSinglyLinkedListToArray() throws MalformedURLException {
		list.clear();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		// Convert the list to an array
		Object[] array = list.toArray();

		// Verify that the elements in the array match the elements in the list
		assertArrayEquals(new URL[] { url3, url2, url1 }, array);
	}

	@Test
	void testSinglyLinkedListIterator() throws MalformedURLException {
		list.clear();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		// Verify that hasNext() works correctly
		assertTrue(iterator.hasNext());
		// Verify that next() returns the elements in the correct order
		assertEquals(url3, iterator.next());
		assertEquals(url2, iterator.next());
		assertEquals(url1, iterator.next());
		// Verify that hasNext() returns false after iterating over all elements
		assertFalse(iterator.hasNext());

	}

	@Test
	void testSinglyLinkedListRemove() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		// Remove the first element using the iterator
		iterator.next();
		iterator.remove();

		// Verify that the first element is removed
		assertEquals(url2, list.getFirst()); // Verify that url2 is now the first element in the list
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListRemoveThrow() throws MalformedURLException {
		SinglyLinkedList<URL> list = new SinglyLinkedList<>();
		Iterator<URL> iterator = list.iterator();
		assertThrows(IllegalStateException.class, () -> iterator.remove());
	}

	@Test
	void testSinglyLinkedListHasNext() throws MalformedURLException {
		list.clear();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		SinglyLinkedList<URL> emptyList = new SinglyLinkedList<>();
		Iterator<URL> iterator2 = emptyList.iterator();

		// Verify that hasNext() works correctly
		assertTrue(iterator.hasNext());
		assertFalse(iterator2.hasNext());

	}

	@Test
	void testSinglyLinkedListNext() throws MalformedURLException {
		list.clear();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), url3);
	}

	@Test
	void testSinglyLinkedListIsEmpty() throws MalformedURLException {
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	void testSinglyLinkedListHasNextThrow() throws MalformedURLException {
		list.clear();
		URL url1 = new URL("https://example1.com");
		list.insertFirst(url1);

		list.clear();
		Iterator<URL> iterator = list.iterator();

		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}

//edge cases tests: large problem size, duplication, empty problem size.
	@Test
	void testSinglyLinkedListLargeProblemSize() throws MalformedURLException {
		int numElements = 10000;
		for (int i = 0; i < numElements; i++) {
			list.insertFirst(new URL("https://example" + i + ".com"));
		}
		assertEquals(numElements, list.size());
	}

	@Test
	void testSinglyLinkedListLargeDuplication() throws MalformedURLException {
		URL url = new URL("https://example.com");
		list.insertFirst(url);
		list.insertFirst(url);
		assertEquals(2, list.size());
	}

	@Test
	void testSinglyLinkedListMixedDataTypes() throws MalformedURLException {
		String str = "String";
		Integer integer = 123;
		URL url = new URL("https://example.com");
		SinglyLinkedList<String> strList = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();

		strList.insertFirst(str);
		intList.insertFirst(integer);
		list.insertFirst(url);

		// Verify that elements are stored and accessed correctly
		assertEquals(url, list.getFirst());
		assertEquals(integer, intList.getFirst());
		assertEquals(str, strList.getFirst());

	}

	@Test
	void testSinglyLinkedListModificationDuringIteration() throws MalformedURLException {
		list.clear();
		// Insert some elements into the list
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
		list.insertFirst(url2);
		list.insertFirst(url3);

		Iterator<URL> iterator = list.iterator();

		list.insertFirst(new URL("https://example5.com"));
		list.insert(2, new URL("https://example6.com"));

		iterator = list.iterator();
		assertEquals(new URL("https://example5.com"), iterator.next());
		iterator.next();
		assertEquals(new URL("https://example6.com"), iterator.next());

	}

	@Test
	void testSinglyLinkedListInsertAndDeleteCornerCase() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");
		list.insertFirst(url1);
        list.insert(list.size(), url2);// insert at the end
        list.insert(list.size(), url3);// insert at the end again
		   // Insert at the beginning
        URL url4 = new URL("https://example4.com");
        list.insertFirst(url4);
        assertEquals(url4, list.getFirst());

        // Insert at a middle position
        URL url5 = new URL("https://example5.com");
        list.insert(2, url5);
        assertEquals(url5, list.get(2));
		
	//url4, url1, url 5, url2, url3
        // Delete from the first position
        assertEquals(url4, list.deleteFirst());
        assertEquals(4, list.size());
        assertEquals(url1, list.getFirst());

        // Delete from the last position
        assertEquals(url3, list.delete(list.size() - 1));
        assertEquals(3, list.size());

        // Delete from a middle position
        assertEquals(url5, list.delete(1));
        assertEquals(2, list.size());
	}
	
	
}