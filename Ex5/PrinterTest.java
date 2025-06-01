import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PrinterTest {
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
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
            SplayTree.cmp = 0;
            SplayTree.point = 0;
            tree.insert(valuesToAdd.get(i));
            //System.out.println("Porównania: " + SplayTree.cmp);
            //System.out.println("Operacje na wskaźnikach: " + SplayTree.point);
            System.out.println("Wysokość drzewa: " + tree.height());
            System.out.println("insert " + valuesToAdd.get(i));
            try {
                OutputStreamWriter out = new OutputStreamWriter(System.out);
                tree.printTree(out);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < n; i++) {
            SplayTree.cmp = 0;
            SplayTree.point = 0;
            tree.deleteNode(valuesToDelete.get(i));
            //System.out.println("Porównania: " + SplayTree.cmp);
            //System.out.println("Operacje na wskaźnikach: " + SplayTree.point);
            System.out.println("Wysokość drzewa: " + tree.height());
            System.out.println("delete " + valuesToDelete.get(i));
            try {
                OutputStreamWriter out = new OutputStreamWriter(System.out);
                if (tree != null) {
                    tree.printTree(out);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
