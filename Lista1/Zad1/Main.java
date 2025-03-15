public class Main{
    public static void main (String [] args){
        MyStack<Integer> stack = new MyStack<>();
        MyQueue<Integer> queue = new MyQueue<>();

        System.out.println("Stack insertion");
        for (int i = 1; i <= 50; i++){
            stack.push(i);
            System.out.println(stack.peek());
        }
        System.out.println("Stack removal");
        for (int i = 1; i <= 50; i++){
            System.out.println(stack.pop());
        }
        System.out.println("Queue insertion");
        for (int i = 1; i <= 50; i++){
            queue.add(i);
            System.out.println(i);
        }
        System.out.println("Queue removal");
        for (int i = 1; i <= 50; i++){
            System.out.println(queue.remove());
        } 
    }
}