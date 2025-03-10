public interface Stack<T> {
    void push(T node);
    T pop();
    T peek();
    boolean isEmpty();
}