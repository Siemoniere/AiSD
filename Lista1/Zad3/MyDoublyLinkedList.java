import java.util.Random;

public class MyDoublyLinkedList implements DoublyLinkedList {

    public static class Node {
        int data;
        Node next;
        Node prev;
    
        public Node(int data){
            this.data = data;
        }
    }

    private Node head;
    private int size = 0;

    @Override
    public void push(int value){
        Node node = new Node(value);
        if (head == null){
            head = node;
            head.next = head.prev = head;
        } else {
            Node current = head;
            while (current.next != head){
                current = current.next;
            }
            current.next = node;
            node.prev = current;
            node.next = head;
            head.prev = node;
        }
        size++;
    }

    @Override
    public void pop(int value){
        if(size == 0){
            System.out.println("List is empty!");
            return;
        }
        Node current = head;
        int count = 0;
        while(current.data != value){
            current = current.next;
            count++;
            if(count == size){
                System.out.println("There is no such a value!");
                return;
            }
        }
        Node temp1 = current.prev;
        temp1.next = current.next;
        
        Node temp2 = current.next;
        temp2.prev = current.prev;
        size--;
    }

    @Override
    public int search(int value){
        if(size == 0){
            return 0;
        }
        Random rand = new Random();
        boolean direction = rand.nextBoolean();
        Node current = head;
        int count = 0;
        do{
            count++;
            if(current.data == value){
                return count;
            }
            if(direction){
                current = current.next;
            } else {
                current = current.prev;
            }            
        } while(current != head);
        return count;
    }

    @Override
    public void display(){
        if(size == 0){
            System.out.println("List is empty!");
            return;
        }
        Node current = head;
        for (int i = 1; i <= size; i++){
            System.out.println(current.data);
            current = current.next;
        }
    }

    @Override
    public void insert(MyDoublyLinkedList l, int value){
        l.push(value);
    }

    @Override
    public void merge(MyDoublyLinkedList l){
        if(l.head == null){
            return;
        }
        Node other = l.head;
        do{
            this.push(other.data);
            other = other.next;
        } while (other != l.head);
    }
}