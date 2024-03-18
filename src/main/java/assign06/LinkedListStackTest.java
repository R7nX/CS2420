package assign06;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListStackTest {
	private LinkedListStack<URL> list;

	@BeforeEach
	void setUp() throws Exception {
		LinkedListStack<URL> list = new LinkedListStack<URL>();
		this.list = list;
	}

	@Test
	void testLinkedListStackClear() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		list.push(url1);
		list.push(url2);
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	void testLinkedListStackIsEmpty() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");

		list.push(url1);
		list.push(url2);
		list.push(url3);
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	void testLinkedListStackPeek() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");

		list.push(url1);
		list.push(url2);
		list.push(url3);

		assertEquals(url3, list.peek());
	}

	@Test
	void testLinkedListStackPop() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");

		list.push(url1);
		list.push(url2);
		list.push(url3);

		assertEquals(3, list.size());

		assertEquals(url3, list.pop());
		assertEquals(2, list.size());

	}

	@Test
	void testLinkedListStackPush() throws MalformedURLException {
		URL url1 = new URL("https://example1.com");
		for (int i = 0; i < 100; i++) {
			list.push(url1);
		}
		assertEquals(100, list.size());
	}

	@Test
	void testLinkedListStackSize() throws MalformedURLException {
		list.clear();
		assertEquals(0, list.size());
		URL url1 = new URL("https://example1.com");
		URL url2 = new URL("https://example2.com");
		URL url3 = new URL("https://example3.com");

		list.push(url1);
		list.push(url2);
		list.push(url3);

		assertEquals(3, list.size());

	}

	// Edge cases
	@Test
	void testLinkedListStackPeekThrow() throws MalformedURLException {
		assertThrows(NoSuchElementException.class, () -> list.peek());
	}
	
	@Test
	void testLinkedListStackPopThrow() throws MalformedURLException {
		
	}
	
	@Test
	void testLinkedListStackPushThrow() throws MalformedURLException {
		
	}
}
