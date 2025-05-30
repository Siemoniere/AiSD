//Red Black Tree
import java.io.OutputStreamWriter;
import java.io.IOException;
enum Color {
    RED, BLACK
}
class Node<T extends Comparable<T>> {
    T data;
    Color color;
    Node<T> left, right, parent;

    Node(T data) {
        this.data = data;
        this.color = Color.RED; 
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;
    private final Node<T> TNULL; // Sentinel node for null references
    public static int point = 0; // Points for the tree
    public static int cmp = 0; // Comparison count
    // Constructor to initialize the Red-Black Tree
    public RedBlackTree() {
        TNULL = new Node<>(null);
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    private void preOrderHelper(Node<T> node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Function to start preorder traversal
    public void preorder() {
        preOrderHelper(this.root);
    }

    // Inorder traversal helper function
    private void inOrderHelper(Node<T> node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    // Function to start inorder traversal
    public void inorder() {
        inOrderHelper(this.root);
    }

    // Postorder traversal helper function
    private void postOrderHelper(Node<T> node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Function to start postorder traversal
    public void postorder() {
        postOrderHelper(this.root);
    }

    // Function to perform left rotation
    private void leftRotate(Node<T> x) {
        point++; // Increment point for left rotation
        Node<T> y = x.right;
        point++;
        x.right = y.left;
        point++; // Increment point for accessing y.left
        if (y.left != TNULL) {
            point++;
            y.left.parent = x;
        }
        point++;
        y.parent = x.parent;
        point++;
        if (x.parent == null) {
            point++;
            this.root = y;
        } else if (x == x.parent.left) {
            point++;
            x.parent.left = y;
            point++;
        } else {
            point++;
            x.parent.right = y;
            point++;
        }
        y.left = x;
        point++;
        x.parent = y;
        point++;
    }

    // Function to perform right rotation
    private void rightRotate(Node<T> x) {
        point++;
        Node<T> y = x.left;
        point++;
        x.left = y.right;
        point++;
        if (y.right != TNULL) {
            point++;
            y.right.parent = x;
        }
        point++;
        y.parent = x.parent;
        point++;
        if (x.parent == null) {
            point++;
            this.root = y;
        } else if (x == x.parent.right) {
            point++;
            x.parent.right = y;
            point++;
        } else {
            point++;
            x.parent.left = y;
            point++;
        }
        point++;
        y.right = x;
        point++;
        x.parent = y;
    }

    // Function to insert a new node
    public void insert(T key) {
        Node<T> node = new Node<>(key);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED; // New node must be red

        Node<T> y = null;
        Node<T> x = this.root;

        // Find the correct position to insert the new node
        while (x != TNULL) {
            point++;
            cmp++;
            y = x;
            if (node.data.compareTo(x.data) < 0) {
                point++;
                x = x.left;
            } else {
                point++;
                x = x.right;
            }
        }

        node.parent = y;
        point++;
        if (y == null) {
            root = node;
        } else if (node.data.compareTo(y.data) < 0) {
            cmp++;
            point++;
            y.left = node;
        } else {
            point++;
            cmp++;
            y.right = node;
        }

        // Fix the tree if the properties are violated
        point++;
        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }
        point++;
        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Function to fix violations after insertion
    private void fixInsert(Node<T> k) {
        Node<T> u;
        while (k.parent.color == Color.RED) {  
            point++;
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    point++;
                    k = k.parent.parent;
                } else {
                    point++;
                    if (k == k.parent.left) {
                        point++;
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                point++;
                u = k.parent.parent.right;

                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    point++;
                    k = k.parent.parent;
                } else {
                    point++;
                    if (k == k.parent.right) {
                        point++;
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            point++;
            if (k == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    public void delete(T key) {
        deleteNodeHelper(root, key);
    }

    private void deleteNodeHelper(Node<T> node, T key) {
        // Znajdź węzeł do usunięcia
        Node<T> z = TNULL;
        Node<T> x, y;
        while (true) {
            point++; // odczyt node (sprawdzenie != TNULL)
            if (node == TNULL) break;

            cmp++; // porównanie kluczy w equals
            if (node.data.equals(key)) {
                point++; // przypisanie z = node
                z = node;
                break;
            }

            cmp++; // porównanie kluczy w compareTo
            if (key.compareTo(node.data) < 0) {
                point++; // odczyt pola left
                node = node.left;
            } else {
                point++; // odczyt pola right
                node = node.right;
            }
        }

        if (z == TNULL) {
            System.out.println("Nie znaleziono klucza do usunięcia w drzewie");
            return;
        }
        point++;
        y = z;
        Color yOriginalColor = y.color;
        point++;
        if (z.left == TNULL) {
            point++;
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            point++;
            x = z.left;
            point++;
            rbTransplant(z, z.left);
        } else {
            point++;
            y = minimum(z.right);
            point++;
            yOriginalColor = y.color;
            point++;
            x = y.right;
            point++;
            if (y.parent == z) {
                point++;
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                point++;
                y.right = z.right;
                point++;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            point++;
            y.left = z.left;
            point++;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    private void rbTransplant(Node<T> u, Node<T> v) {
        point++;
        if (u.parent == null) {
            point++;
            root = v;
        } else if (u == u.parent.left) {
            point++;
            point++;
            u.parent.left = v;
        } else {
            point++;
            point++;
            u.parent.right = v;
        }
        point++;
        v.parent = u.parent;
    }

    private void fixDelete(Node<T> x) {
        Node<T> s;
        while (x != root && x.color == Color.BLACK) {
            point++;
            if (x == x.parent.left) {
                point++;
                s = x.parent.right;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    point++;
                    s = x.parent.right;
                }
                if (s.left.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    point++;
                    x = x.parent;
                } else {
                    if (s.right.color == Color.BLACK) {
                        s.left.color = Color.BLACK;
                        s.color = Color.RED;
                        rightRotate(s);
                        point++;
                        s = x.parent.right;
                    }
                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    point++;
                    x = root;
                }
            } else {
                point++;
                s = x.parent.left;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    point++;
                    s = x.parent.left;
                }
                if (s.left.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    point++;
                    x = x.parent;
                } else {
                    if (s.left.color == Color.BLACK) {
                        s.right.color = Color.BLACK;
                        s.color = Color.RED;
                        leftRotate(s);
                        point++;
                        s = x.parent.left;
                    }
                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    point++;
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    private Node<T> minimum(Node<T> node) {
        while (node.left != TNULL) {
            point++;
            node = node.left;
        }
        return node;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node<T> node) {
        if (node == TNULL) {
            return 0;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree(OutputStreamWriter out) throws IOException {
        printTreeHelper(root, out, "", true);
    }

    private void printTreeHelper(Node<T> node, OutputStreamWriter out, String indent, boolean isRight) throws IOException {
        if (node == TNULL) return;

        printTreeHelper(node.right, out, indent + (isRight ? "        " : " |      "), true);

        out.write(indent);
        out.write(isRight ? " /" : " \\");
        out.write("----- ");
        if (node.data == null) {
            out.write("<null>");
        } else {
            out.write(node.data.toString());
        }
        out.write('\n');

        printTreeHelper(node.left, out, indent + (isRight ? " |      " : "        "), false);
    }

    public int getPoint() {
        return point;
    }
    public int getCmp() {
        return cmp;
    }
}