package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void insertFirst(T element) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException{
        // TODO Auto-generated method stub

    }

    @Override
    public T getFirst() throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T deleteFirst() throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

	@Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public T[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	private class SinglyListIterator implements Iterator<T> {
        private Node<T> curr = head;
        private Node<T> pre = null;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = curr.data;
            pre = curr;
            curr = curr.next;
            return data;
        }

        @Override
        public void remove() {
            if (pre == null) {
                throw new NoSuchElementException();
            }
            pre.next = curr;
        }
    }

}
