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
        is.sort();
        System.out.printf("Swaps: %d\n", is.getSwap());
        System.out.printf("Compares: %d\n", is.getCmp());
        scanner.close();
    }
}