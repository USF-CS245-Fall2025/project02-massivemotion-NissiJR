<<<<<<< HEAD
public class DummyHeadLinkedList<T> implements List<T> {
    private static final class Node<T> {
        T value;
        Node<T> next;
        Node (T val) {
            this.value = val;
        }
    }
    private final Node<T> head;
    private Node<T> tail;
    private int n;

    public DummyHeadLinkedList() {
        head = new Node<>(null);
        tail = head;
        n = 0;
    }

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

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        tail.next = node;
        tail = node;
        n++;
        return true;
    }

    @Override
    public T get(int index) {
        check(index);
        Node<T> curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

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

    @Override
    public int size() {
        return n;
    }

    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
=======
public class DummyHeadLinkedList<T> implements List<T> {
    private static final class Node<T> {
        T value;
        Node<T> next;
        Node (T val) {
            this.value = val;
        }
    }
    private final Node<T> head;
    private Node<T> tail;
    private int n;

    public DummyHeadLinkedList() {
        head = new Node<>(null);
        tail = head;
        n = 0;
    }

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

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        tail.next = node;
        tail = node;
        n++;
        return true;
    }

    @Override
    public T get(int index) {
        check(index);
        Node<T> curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

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

    @Override
    public int size() {
        return n;
    }

    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
>>>>>>> 548f765d72bf670da0298225c041b450b4ecbec2
}