//kruskal algorithm implementation
import java.util.Arrays;
import java.util.Scanner;
public class Kruskal {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextDouble();
            }
        }
        kruskal(graph);
        scanner.close();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms");
    }

    public static void kruskal(double[][] graph) {
        int V = graph.length;
        Edge[] edges = new Edge[V * (V - 1) / 2];
        int edgeCount = 0;

        // Create a list of edges
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (graph[i][j] != 0.0) {
                    edges[edgeCount++] = new Edge(i, j, graph[i][j]);
                }
            }
        }

        // Sort edges based on weight
        Arrays.sort(edges, 0, edgeCount);

        DisjointSet ds = new DisjointSet(V);
        double mstWeight = 0.0;

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges[i];
            if (ds.find(edge.src) != ds.find(edge.dest)) {
                ds.union(edge.src, edge.dest);
                mstWeight += edge.weight;
                System.out.printf("%d - %d: %.2f%n", edge.src, edge.dest, edge.weight);
            }
        }

        System.out.printf("Total weight of MST: %.2f%n", mstWeight);
    }

    static class Edge implements Comparable<Edge> {
        int src, dest;
        double weight;

        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }

    static class DisjointSet {
        private int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]); // Path compression
            }
            return parent[u];
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }    
}
