import java.io.OutputStreamWriter;
import java.io.IOException;
public class Node<T extends Comparable<T>> {
    T value;
    Node<T> left, right;
    public static int cmp = 0, point = 0;
    public void insertToTree(T v) {
        if (value == null) {
            value = v;
            return;
        }
        cmp++;
        if (v.compareTo(value) < 0) {
            point++;
            if (left == null) {
                point++;
                left = new Node<T>();
            }
            point++;
            left.insertToTree(v);
        } else {
            point++;
            if (right == null) {
                point++;
                right = new Node<T>();
            }
            point++;
            right.insertToTree(v);
        }
    }
    public Node<T> deleteFromTree(T v) {
        if (value == null) {
            return this;
        }
        cmp++;
        if (v.compareTo(value) < 0) {
            point++;
            if (left != null) {
                point++;
                left = left.deleteFromTree(v);
            }
        } else if (v.compareTo(value) > 0) {
            cmp++;
            point++;
            if (right != null) {
                point++;
                right = right.deleteFromTree(v);
            }
        } else {
            cmp++;
            // Node to be deleted found
            point = point + 2;
            if (left == null && right == null) {
                return null; // remove this node
            } else if (left == null) {
                point++;
                return right;
            } else if (right == null) {
                point = point + 2;
                return left;
            } else {
                point += 2;
                Node<T> minNode = right;
                point++;
                while (true) {
                    point++;
                    if (minNode.left == null) break;
                    minNode = minNode.left;
                    point++;
                }
                this.value = minNode.value;
                point++;
                point++;
                right = right.deleteFromTree(minNode.value);
            }
        }
        return this;
    }

    public int height() {
        if (value == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;

        point++;
        if (left != null) {
            leftHeight = left.height();
        }

        point++;
        if (right != null) {
            rightHeight = right.height();
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree(OutputStreamWriter out) throws IOException {
        if (right != null) {
            right.printTree(out, true, "");
        }
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, "");
        }
    }
    private void printNodeValue(OutputStreamWriter out) throws IOException {
        if (value == null) {
            out.write("<null>");
        } else {
            out.write(value.toString());
        }
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (right != null) {
            right.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }
    public int getPoint() {
        return point;
    }
    public int getCmp() {
        return cmp;
    }
}