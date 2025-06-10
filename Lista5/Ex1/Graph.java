import java.util.Random;

public class Graph {
    public static void main(String[] args) {
        Random random = new Random();
        int n = Integer.parseInt(args[0]);
        double[][] matrix = new double[n][n];
        System.out.println(n);
        // Fill upper triangle and diagonal
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0.0;
                } else {
                    double weight = random.nextDouble(1); // Random weight between 0 and 1
                    matrix[i][j] = weight;
                    matrix[j][i] = weight;
                }
            }
        }

        // Print matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
