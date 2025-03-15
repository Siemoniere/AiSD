public class MyQueue<T> implements Queue<T>{

    private static class Node<T>{
        T data;
        Node<T> previous;

        Node(T data){
            this.data = data;
        }
    }
    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T node){
        Node<T> newNode = new Node<>(node);
        if (first != null){
            first.previous = newNode;
        }
        first = newNode;
        if (last == null){
            last = first;
        }

    }

    @Override
    public T remove(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty!");
        }
        T data = last.data;
        last = last.previous;
        if(last == null){
            first = null;
        }
        return data;
    }

    @Override
    public T peek(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty!");
        }
        return last.data;
    }

    @Override
    public boolean isEmpty(){
        if(first == null){
            return true;
        } else return false;
    }
}