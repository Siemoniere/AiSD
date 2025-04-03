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
        MergeSort ms = new MergeSort(n, a);
        ArrayList<Integer> old = new ArrayList<>();
        ms.saveOld(old);
        ArrayList<Integer> sorted = ms.sort(aCopy);
        ms.setSorted(sorted);
        if (n < 40){
            System.out.println("Oto nieposortowana tablica: ");
            ms.display(old);
            System.out.println("Posortowana tablica: ");
            ms.display(a); 
        }

        System.out.printf("Swaps: %d\n", ms.getSwap());
        System.out.printf("Compares: %d\n", ms.getCmp());

        if (ms.check()) {
            System.out.println("Poprawnie");
        } else {
            System.out.println("Error");
        }
        scanner.close();
    }
}