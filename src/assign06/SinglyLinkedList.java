package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void insertFirst(T element) {
        head = new Node<>(element, head);
        size++;
    }

    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException{
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            insertFirst(element);
        else{
            Node<T> prevNode = getNode(index - 1);
            prevNode.next = new Node<>(element, prevNode.next);
            size++;
        }
    }

    private Node<T> getNode(int pos){
        Node<T> temp = head;
        for (int i = 0; i < pos; i++){
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
        return head.data;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node<T> currNode = getNode(index);
        return currNode.data;
    }

    @Override
    public T deleteFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
        T temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return deleteFirst();
        else{
            Node<T> prevNode = getNode(index - 1);
            T temp = prevNode.next.data;
            prevNode.next = prevNode.next.next;
            size--;
            return temp;
        }
    }

    @Override
    public int indexOf(T element) {
        Node<T> currNode = head;
        for (int i = 0; i < size; i++){
            if (currNode.data.equals(element))
                return i;
            currNode = currNode.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

	@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T[] toArray() {
        Object[] array = new Object[size];
        Node<T> currNode = head;
        for (int i = 0; i < size; i++){
            array[i] = currNode.data;
            currNode = currNode.next;
        }
        return (T[]) array;
    }

	@Override
	public Iterator<T> iterator() {
        return new SinglyListIterator();
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
            if (pre == head)
                head = curr;
            else
                pre.next = curr;

            pre = null;
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
