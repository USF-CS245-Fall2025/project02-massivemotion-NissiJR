package lists;

public class ArrayList<T> implements List<T> {
    private Object[] data;
    private int n;

    public ArrayList() {
        this.data = new Object[10];
        this.n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override 
    public void add(int index, T element) {
        check(index);
        data[index] = element;
        n++;
    }

    @Override 
    public boolean add(T element) {
        data[n] = element;
        n++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        check(index);
        return (T) data[index];
    }
 
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

    private void check (int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
    }
}