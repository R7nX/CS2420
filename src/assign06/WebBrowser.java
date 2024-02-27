package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
	private SinglyLinkedList<String> back;
	private LinkedListStack<String> forward;

	public WebBrowser() {

	}

	public WebBrowser(SinglyLinkedList<URL> history) {

	}

	public void visit(URL webpage) {

	}

	public URL back() throws NoSuchElementException {
		return null;

	}

	public URL forward() throws NoSuchElementException {
		return null;

	}

	public SinglyLinkedList<URL> history() {
		return null;

	}

}
