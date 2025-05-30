import java.util.ArrayList;
import java.util.Scanner;

public class PrinterTest {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>();
        ArrayList<Integer> valuesToAdd = new ArrayList<>();
        ArrayList<Integer> valuesToDelete = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            valuesToAdd.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            valuesToDelete.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            Node.cmp = 0;
            Node.point = 0;
            root.insertToTree(valuesToAdd.get(i));
            System.out.println("Porównania: " + Node.cmp);
            System.out.println("Operacje na wskaźnikach: " + Node.point);
            System.out.println("Wysokość drzewa: " + root.height());
        }
        for (int i = 0; i < n; i++) {
            Node.cmp = 0;
            Node.point = 0;
            root = root.deleteFromTree(valuesToDelete.get(i));
            System.out.println("Porównania: " + Node.cmp);
            System.out.println("Operacje na wskaźnikach: " + Node.point);
            if (root != null) {
                System.out.println("Wysokość drzewa: " + root.height());
            } else {
                System.out.println("Wysokość drzewa: 0");
            }

        }
        scanner.close();
    }
}
