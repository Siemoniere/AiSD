//Prim algorithm implementation in Java
import java.util.Scanner;
public class Prim {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextDouble();
            }
        }
        prim(graph);
        scanner.close();
    }

    public static void prim(double[][] graph) {
        int V = graph.length;
        boolean[] inMST = new boolean[V];
        double[] key = new double[V];
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            key[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }
        
        key[0] = 0.0; // Start from the first vertex
        parent[0] = -1; // First node is always root of MST

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, inMST);
            inMST[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0.0 && !inMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    private static int minKey(double[] key, boolean[] inMST) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printMST(int[] parent, double[][] graph) {
        for (int i = 1; i < parent.length; i++) {
            System.out.println(parent[i] + " " + i);
        }
    }
}