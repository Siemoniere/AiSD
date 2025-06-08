import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++){
            a.add(scanner.nextInt());
        }
        ArrayList<Integer> aCopy = new ArrayList<>(a);
        Hybrid h = new Hybrid(n, aCopy);
        ArrayList<Integer> old = new ArrayList<>();
        h.saveOld(old);
        ArrayList<Integer> sorted = h.quickSort(aCopy);
        h.setSorted(sorted);
        if (n < 40){
            System.out.println("Oto nieposortowana tablica: ");
            h.display(old);
            System.out.println("Posortowana tablica: ");
            h.display(aCopy); 
        }

        System.out.printf("Swaps: %d\n", h.getSwap());
        System.out.printf("Compares: %d\n", h.getCmp());

        if (h.check()) {
            System.out.println("Poprawnie");
        } else {
            System.out.println("Error");
        }
        scanner.close();
    }
}