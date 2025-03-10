public class MyStack<T> implements Stack<T> {
    
    private static class Node<T>{
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
        }
    }
    private Node<T> top;

    @Override
    public void push(T node){
        Node<T> newNode = new Node<>(node);
        newNode.next = top;
        top = newNode;
    }
    @Override
    public T pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty!");
        }
        T value = top.data;
        top = top.next;
        return value;
    }
    @Override
    public T peek(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty!");
        }
        T value = top.data;
        return value;
    }
    @Override
    public boolean isEmpty(){
        if(top == null){
            return true;
        } else return false;
    }
}