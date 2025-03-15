public interface Queue<T> {
    void add(T data);
    T remove();
    T peek();
    boolean isEmpty();
}