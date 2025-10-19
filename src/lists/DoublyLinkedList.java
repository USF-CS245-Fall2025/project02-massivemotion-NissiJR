package lists;

public class DoublyLinkedList<T> implements List<T> {
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

    public DoublyLinkedList() {
        head = null;
        tail = null;
        n = 0;
    }

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

    @Override
    public int size(){
        return n;
    }

    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutofBoundsExceptions();
        }
    }
}