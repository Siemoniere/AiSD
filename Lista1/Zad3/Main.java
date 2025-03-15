import java.util.Random;
public class Main{
    public static void main (String[] args){
        MyDoublyLinkedList l1 = new MyDoublyLinkedList();
        MyDoublyLinkedList l2 = new MyDoublyLinkedList();
        Random rand = new Random();
        for (int i = 1; i <= 10; i++){
            l1.push(rand.nextInt(90) + 10);
            l2.push(rand.nextInt(90) + 10);
        }
        System.out.println("LinkedList 1:");
        l1.display();
        System.out.println("LinkedList 2:");
        l2.display();
        merge(l1, l2);
        System.out.println("After merge:");
        l1.display();

        int [] t = new int[10000];
        for (int i = 0; i < 10000; i++){
            t[i] = rand.nextInt(100001);
        }
        MyDoublyLinkedList l = new MyDoublyLinkedList();
        for (int i = 0; i < 10000; i++){
            l.push(t[i]);
        }

        //first case
        int total1 = 0;
        for (int i = 0; i < 1000; i++){
            total1 += l.search(t[rand.nextInt(10000)]);
        }

        //second case
        int total2 = 0;
        for(int i = 0; i < 1000; i++){
            total2 += l.search(rand.nextInt(100001));
        }
        System.out.println("First case: " + total1);
        System.out.println("Second case: " + total2);

    }

    public static void merge (MyDoublyLinkedList l1, MyDoublyLinkedList l2){
        l1.merge(l2);
    }
}