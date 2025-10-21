/**
 * A singly linked list implementation with a dummy head node.
 * This class provides basic list operations such as add, get, remove, and size.   
 * @param <T> the type of elements stored in this list
 * @author NissiJR
 * @version 8.0
 */
public class DummyHeadLinkedList<T> implements List<T> {
    /**
     * A node in the linked list.
     * @param <T> the type of value stored in the node
     */
    private static final class Node<T> {
        T value;
        Node<T> next;
        /**
         * Constructs a Node with the specified value.
         * @param val the value to be stored in the node
         */
        Node (T val) {
            this.value = val;
        }
    }
    private final Node<T> head;
    private Node<T> tail;
    private int n;
    /**
     * Constructs an empty DummyHeadLinkedList.
     * Initializes the dummy head and tail to the same node.
     */
    public DummyHeadLinkedList() {
        head = new Node<>(null);
        tail = head;
        n = 0;
    }

    /**
     * Adds an element at the specified index in the list.
     * @param index the index at which the element should be added
     * @param element the element to be added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        check(index);
        Node<T> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> node = new Node<>(element);
        node.next = prev.next;
        prev.next = node;
        if (index == n) {
            tail = node;
        }
        n++;
    }

    /**
     * Adds an element to the end of the list.
     * @param element the element to be added
     * @return true after the element is added
     */
    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        tail.next = node;
        tail = node;
        n++;
        return true;
    }

    /**
     * Retrieves the element at the specified index in the list.
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        check(index);
        Node<T> curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Removes the element at the specified index in the list.
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        check(index);
        Node<T> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> removed = prev.next;
        prev.next = removed.next;
        if (removed == tail) {
            tail = prev; 
        }
        n--;
        return removed.value;
    }

    /**
     * Returns the number of elements in the list.
     * @return the size of the list
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Checks if the given index is within the valid range of the list.
     * 
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
}