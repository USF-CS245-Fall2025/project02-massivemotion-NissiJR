/**
 * A simple List interface.
 */
public interface List<T> {
    /**
     * Adds an element at the specified index.
     * @param index the index at which to add the element
     * @param element the element to add
     * @return void
     */
    public void add (int index, T element);
    /**
     * Adds an element to the end of the list.
     * @param element the element to add
     * @return true if the element was added successfully
     */
    public boolean add (T element);
    /**
     * Gets the element at the specified index.
     * @param index the index of the element to get
     * @return the element at the specified index
     */
    public T get (int index);
    /**
     * Removes the element at the specified index.
     * @param index the index of the element to remove
     * @return the removed element
     */
    public T remove (int index);
    /**
     * Returns the size of the list.
     * @return the number of elements in the list
     */
    public int size ();
}
