import java.util.*;

class Tree {
    private Node root;

    static class Node {
        int key;
        int T;
        int round;
        List<Node> children;

        public Node(int key) {
            this.key = key;
            this.T = 0;
            this.round = -1;
            this.children = new ArrayList<>();
        }
    }

    public Tree(int rootKey) {
        this.root = new Node(rootKey);
    }

    public void addChild(int parentKey, int childKey) {
        Node parentNode = findNodeDFS(root, parentKey);
        if (parentNode != null) {
            Node childNode = new Node(childKey);
            parentNode.children.add(childNode);
        }
    }

    private Node findNodeDFS(Node node, int targetKey) {
        if (node == null) return null;
        if (node.key == targetKey) return node;

        for (Node child : node.children) {
            Node found = findNodeDFS(child, targetKey);
            if (found != null) return found;
        }
        return null;
    }

    public void computePropagationTimes() {
        computeT_rec(root);
    }

    private int computeT_rec(Node node) {
        if (node == null) return 0;

        if (node.children.isEmpty()) {
            node.T = 0;
            return 0;
        }

        for (Node child : node.children) {
            computeT_rec(child);
        }

        node.children.sort(Comparator.comparingInt(a -> a.T));

        int best = 0;
        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            int currentTime = i + 1;
            best = Math.max(best, child.T + currentTime);
        }

        node.T = best;
        return best;
    }

    public void assignRounds() {
        if (root == null) return;
        root.round = 0;
        assignRounds_rec(root);
    }

    private void assignRounds_rec(Node node) {
        if (node == null || node.children.isEmpty()) return;

        node.children.sort((a, b) -> Integer.compare(b.T, a.T));

        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            child.round = node.round + i + 1;
        }

        for (Node child : node.children) {
            assignRounds_rec(child);
        }
    }

    public void printNice() {
        if (root == null) return;
        System.out.println("[key=" + root.key + ", T=" + root.T + ", round=" + root.round + "]");
        for (int i = 0; i < root.children.size(); i++) {
            boolean lastChild = (i + 1 == root.children.size());
            printNice_rec(root.children.get(i), "", lastChild);
        }
    }

    private void printNice_rec(Node node, String prefix, boolean isLast) {
        if (node == null) return;

        System.out.print(prefix);
        System.out.print(isLast ? "└─ " : "├─ ");
        System.out.println("[key=" + node.key + ", T=" + node.T + ", round=" + node.round + "]");

        String newPrefix = prefix + (isLast ? "   " : "│  ");
        for (int i = 0; i < node.children.size(); i++) {
            boolean lastChild = (i + 1 == node.children.size());
            printNice_rec(node.children.get(i), newPrefix, lastChild);
        }
    }
    private static Tree.Node buildTreeFromRoot(int rootKey, Map<Integer, List<Integer>> adj) {
        Map<Integer, Tree.Node> nodes = new HashMap<>();
        for (Integer key : adj.keySet()) {
            nodes.put(key, new Tree.Node(key));
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(rootKey);
        visited.add(rootKey);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Tree.Node currentNode = nodes.get(current);
            for (int neighbor : adj.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    currentNode.children.add(nodes.get(neighbor));
                }
            }
        }
        return nodes.get(rootKey);
    }
private static int findMaxRound(Tree.Node node) {
    if (node == null) return -1;
    int max = node.round;
    for (Tree.Node child : node.children) {
        int childMax = findMaxRound(child);
        if (childMax > max) {
            max = childMax;
        }
    }
    return max;
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> edges = new ArrayList<>();

        // Wczytaj wszystkie pary (krawędzie) aż do końca wejścia
        while (scanner.hasNextInt()) {
            int parent = scanner.nextInt();
            if (scanner.hasNextInt()) {
                int child = scanner.nextInt();
                edges.add(new int[]{parent, child});
            } else {
                break;
            }
        }
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // Zbierz wszystkie klucze
        Set<Integer> allNodes = new HashSet<>();
        Set<Integer> children = new HashSet<>();
        for (int[] edge : edges) {
            allNodes.add(edge[0]);
            allNodes.add(edge[1]);
            children.add(edge[1]);
        }
        // Stwórz mapę wszystkich węzłów
        Map<Integer, Tree.Node> nodes = new HashMap<>();
        for (int node : allNodes) {
            nodes.put(node, new Tree.Node(node));
        }

        // Połącz węzły
        for (int[] edge : edges) {
            Tree.Node parent = nodes.get(edge[0]);
            Tree.Node child = nodes.get(edge[1]);
            parent.children.add(child);
        }
        scanner.close();
        //to samo ale dla rootKey = 0, 1, 2, 3, 4
        ArrayList<Integer> maxRounds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (!adj.containsKey(i)) {
                System.out.println("Tree with root " + i + ": brak takiego węzła.");
                continue;
            }
            Tree.Node root = buildTreeFromRoot(i, adj);
            Tree tree1 = new Tree(i);
            tree1.root = root;
            tree1.computePropagationTimes();
            tree1.assignRounds();
            System.out.println("Tree with root " + i + ":");
            tree1.printNice();
            int maxRound = findMaxRound(tree1.root);
            maxRounds.add(maxRound);
            System.out.println("Max round: " + maxRound);
        }

        System.out.println("\nNajlepszy (najmniejszy) max round: " + Collections.min(maxRounds));
        System.out.println("Najgorszy (największy) max round: " + Collections.max(maxRounds));
        System.out.println("Średni max round: " + maxRounds.stream().mapToInt(Integer::intValue).average().orElse(0.0));
    }
}