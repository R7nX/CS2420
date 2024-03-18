package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
	private SinglyLinkedList<T> singlyLinkedList;
	public LinkedListStack() {
		singlyLinkedList = new SinglyLinkedList<>();
	}

	@Override
	public void clear() {
		singlyLinkedList.clear();
	}

	@Override
	public boolean isEmpty() {
		return singlyLinkedList.isEmpty();
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}
		return singlyLinkedList.getFirst();
	}

	@Override
	public T pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}
		T temp = singlyLinkedList.getFirst();
		singlyLinkedList.deleteFirst();
		return temp;
	}

	@Override
	public void push(T element) {
		singlyLinkedList.insertFirst(element);
	}

	@Override
	public int size() {
		return singlyLinkedList.size();
	}
}
