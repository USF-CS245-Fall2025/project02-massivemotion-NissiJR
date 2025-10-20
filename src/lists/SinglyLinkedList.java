package lists;

public class SinglyLinkedList<T> implements List<T> {
    private static final class Node<T> {
        T value;
        Node<T> next;
        Node (T val) {
            this.value = val;
        }
    } 

    private Node<T> head;
    private Node<T> tail;
    private int n;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        n = 0;
    }

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

    @Override
    public T get(int index) {
        check(index);
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

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

    @Override
    public int size(){
        return n;
    }

    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
}