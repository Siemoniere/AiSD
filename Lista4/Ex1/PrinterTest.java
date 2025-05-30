import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

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
            root.insertToTree(valuesToAdd.get(i));
            System.out.println("insert " + valuesToAdd.get(i));
            System.out.println("height: " + root.height());
            try {
                OutputStreamWriter out = new OutputStreamWriter(System.out);
                root.printTree(out);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < n; i++) {
            root = root.deleteFromTree(valuesToDelete.get(i));
            System.out.println("delete " + valuesToDelete.get(i));
            System.out.println("height: " + (root == null ? 0 : root.height()));
            try {
                OutputStreamWriter out = new OutputStreamWriter(System.out);
                if (root != null) {
                    root.printTree(out);
                } else {
                    System.out.println("Tree is now empty.");
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}
