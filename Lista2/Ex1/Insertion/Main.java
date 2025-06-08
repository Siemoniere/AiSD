import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        InsertionSort is = new InsertionSort(n, a);

        int[] old = new int[n];
        is.saveOld(old);
        is.sort();
        if (n < 40){
            System.out.println("Oto nieposortowana tablica: ");
            is.display(old);
            System.out.println("Posortowana tablica: ");
            is.display(a); 
        }
        System.out.printf("Swaps: %d\n", is.getSwap());
        System.out.printf("Compares: %d\n", is.getCmp());

        if (is.check()) {
            System.out.println("Poprawnie");
        } else {
            System.out.println("Error");
        }
        scanner.close();
    }
}