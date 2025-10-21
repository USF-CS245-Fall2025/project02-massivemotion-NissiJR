/**
 * A generic doubly linked list implementation in Java.
 * This class provides basic list operations such as add, get, remove, and size.
 * 
 * @param <T> the type of elements stored in this list
 * @author NissiJR
 * @version 8.0
 */
public class DoublyLinkedList<T> implements List<T> {
    /**
     * Represents a single node in the doubly linked list.
     * Each node holds a value and pointers to both the next and previous nodes.
     *
     * @param <T> the type of data stored in the node
     */
    private static final class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;
        /**
         * Constructs a node with the specified value.
         *
         * @param val the data to store in this node
         */
        Node (T val) {
            this.value = val;
        }
    } 

    private Node<T> head;
    private Node<T> tail;
    private int n;

    /**
     * Constructs an empty {@code DoublyLinkedList}.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        n = 0;
    }

    /**
    * Adds an element at the specified index in the list.
    * @param index the position to insert the element
    * @param element the element to be added
    * @throws IndexOutOfBoundsException if the index is out of range
    */
    @Override
    public void add(int index, T element) {
        check(index);
        Node <T> node = new Node<>(element);

        if (n == 0) {
            head = tail;;
            head = node;
        } else if (index == 0) {
            node.next = head;
            head.prev = node;
            head = node;
        } else if (index == n) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        } else {
            Node<T> curr;
            if (index < n/2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.next;
                }
            } else {
                curr = tail;
                for (int i = n; i > index; i--) {
                    curr = curr.prev;
                }
            }
            Node<T> before = curr.prev;
            node.prev = before;
            node.next = curr;
            before.next = node;
            curr.prev = node;
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
        if (tail == null) {
            head = tail;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        n++;
        return true;
    }

    /**
    * Retrieves the element at the specified index.
    * @param index the position of the element to retrieve
    * @return the element at the specified index
    * @throws IndexOutOfBoundsException if the index is out of range
    */
    @Override
    public T get(int index) {
        check(index);
        Node<T> curr;
        if (index < n/2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = 0; i > index; i--) {
                curr = curr.prev;
            } 
        }
        return curr.value;
    }

    /**
    * Removes the element at the specified index.
    * @param index the position of the element to remove
    * @return the removed element
    * @throws IndexOutOfBoundsException if the index is out of range
    */
    @Override
    public T remove(int index) {
        check(index);
        Node<T> removed;

        //only element
        if (n == 1) {
            removed = head;
            head = tail;
            tail = null;
        }
        //remove head
        if (index == 0) {
            removed = head;
            head = head.next;
            if (head == null) {
                tail = null; //empty list
            }
        } else if (index == n - 1) {
            removed = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            if (index < n / 2) {
                removed = head;
                for (int i = 0; i < index; i++) {
                    removed = removed.next;
                }
            } else {
                removed = tail;
                for (int i = n - 1; i > index; i--) {
                    removed = removed.prev;
                }
            }
            Node<T> a = removed.prev;
            Node<T> b = removed.next;
            a.next = b;
            b.prev = a;
        }
        n--;
        return removed.value;
    }

    /**
    * Returns the number of elements currently stored in the list.
    *
    * @return the size of the list
    */
    @Override
    public int size(){
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