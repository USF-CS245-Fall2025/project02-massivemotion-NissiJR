/**
 * A simple implementation of a dynamic array-based list structure.
 * This class provides basic list operations such as add, get, remove, and size.
 * 
 * @param <T> the type of elements stored in this list
 * @author NissiJR
 * @version 8.0
 */
public class ArrayList<T> implements List<T> {
    private Object[] data;
    private int n;

    /**
     * Constructs an empty ArrayList with an initial capacity of 10.
     */
    public ArrayList() {
        this.data = new Object[10];
        this.n = 0;
    }
    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Adds an element at the specified index of the list.
     * 
     * @param index the position where the element should be inserted
     * @param element the element to be added
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > n)
     */
    @Override 
    public void add(int index, T element) {
        check(index);
        data[index] = element;
        n++;
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param element the element to be added
     * @return true if the element was successfully added
     */
    @Override 
    public boolean add(T element) {
        data[n] = element;
        n++;
        return true;
    }

    /**
     * Returns the element at the specified position in the list.
     * 
     * @param index the position of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        check(index);
        return (T) data[index];
    }

    /**
     * Removes the element at the specified position in the list.
     * Shifts subsequent elements to the left to fill the gap.
     * 
     * @param index the position of the element to remove
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        check(index);
        @SuppressWarnings("unchecked")
        T old = (T) data[index];

        for (int i = index; i < n - 1; i++) { 
            data[i] = data[i + 1];
        }
        n--;
        data[n] = null;

        return old;
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