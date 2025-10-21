/**
 * A simple implementation of a singly linked list.
 * @param <T> the type of elements in this list
 */
public class SinglyLinkedList<T> implements List<T> {
    /**
     * A node in the singly linked list.
     * @param <T> the type of element stored in the node
     */
    private static final class Node<T> {
        T value;
        Node<T> next;
        /**
         * Constructs a new node with the given value.
         * @param val the value to be stored in the node
         */
        Node (T val) {
            this.value = val;
        }
    } 

    private Node<T> head;
    private Node<T> tail;
    private int n;

    /**
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        n = 0;
    }

    /**
     * Adds the specified element at the specified position in this list.
     * @param index the index at which the specified element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index <
     */
    @Override
    public void add(int index, T element) {
        check(index);
        Node <T> node = new Node<>(element);

        if (index == 0) {
            node.next = head;
            head = node;
            if (n == 0) {
                tail = node;
            }
        } else if (index == n) {
            tail.next = node;
            tail = node;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            } 
            node.next = prev.next;
            prev.next = node;
        }
        n++;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element the element to be appended to this list
     * @return true since the addition is always successful
     */
    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        if (tail == null) {
            head = tail;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        n++;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index <
     */
    @Override
    public T get(int index) {
        check(index);
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index <
     */
    @Override
    public T remove(int index) {
        check(index);
        Node<T> removed;

        //remove head
        if (index == 0) {
            removed = head;
            head = head.next;
            if (head == null) {
                tail = null; //empty list
            }
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i ++) {
                prev = prev.next;
            }
            removed = prev.next;
            prev.next = removed.next;
            if (removed == tail) {
                tail = prev; // removed last node
            }
        }
        n--;
        return removed.value;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size(){
        return n;
    }

    /**
     * Checks if the given index is within the bounds of the list.
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException if the index is out of range (index <
     */
    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
}