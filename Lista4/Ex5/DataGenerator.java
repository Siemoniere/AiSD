import java.util.Random;

public class DataGenerator{
    public static void shuffle(int[] a) {
        Random rand = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java DataGenerator [l|r] n");
            return;
        }
        String mode = args[0];
        int n = Integer.parseInt(args[1]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        switch (mode){
            case "r":
                break;
            case "l":
                shuffle(a);
                 break;
            default:
                System.out.println("Wrong mode: " + mode);
                return;
        }
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
        shuffle(a);
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
        
    }
}