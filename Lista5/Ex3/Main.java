import java.util.Scanner;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int n = scanner.nextInt();
        BH bh1 = new BH();
        BH bh2 = new BH();
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            values.add(scanner.nextInt());
        }
        for (int i = 0; i < n / 2; i++) {
            bh1.cmp = 0;
            bh1.insert(values.get(i));
            System.out.println(count + "; " + bh1.cmp);
            count++;
        }
        for (int i = n / 2; i < n; i++) {
            bh2.cmp = 0;
            bh2.insert(values.get(i));
            System.out.println(count + "; " + bh2.cmp);
            count++;
        }
        bh1.cmp = 0;
        bh1.setHead(bh1.union(bh1.getHead(), bh2.getHead()));
        System.out.println(count + "; " + bh1.cmp);
        for (int i = 0; i < n - 1; i++) {
            bh1.cmp = 0;
            bh1.extractMin();
            System.out.println(count + "; " + bh1.cmp);
            count++;
            if (bh1.isEmpty()) {
                System.out.println("Kopiec jest pusty.");
                break;
            }
        }
        scanner.close();
    }
}
