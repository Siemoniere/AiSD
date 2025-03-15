public class MySinglyLinkedList implements SinglyLinkedList {

    public static class Node {
        int data;
        Node next;
    
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
            head.next = node;
            node.next = head;
        } else {
            Node current = head;
            while (current.next != head){
                current = current.next;
            }
            current.next = node;
            node.next = head;
        }
        size++;
    }

    @Override
    public void pop(int value){
        if(size == 0){
            System.out.println("List is empty!");
            return;
        }
        Node prev = null;
        Node current = head;
        int count = 0;
        while(current.data != value){
            prev = current;
            current = current.next;
            count++;
            if(count == size){
                System.out.println("There is no such a value!");
                return;
            }
        }
        prev.next = current.next;
        size--;
    }

    @Override
    public int search(int value){
        if(size == 0){
            return 0;
        }
        Node current = head;
        int count = 1;
        do{
            if(current.data == value){
                return count;
            }
            current = current.next;
            count++;
        } while(current != head);
        return size + 1;
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
    public void insert(MySinglyLinkedList l, int value){
        l.push(value);
    }

    @Override
    public void merge(MySinglyLinkedList l){
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