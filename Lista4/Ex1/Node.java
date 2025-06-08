import java.io.OutputStreamWriter;
import java.io.IOException;
public class Node<T extends Comparable<T>> {
    T value;
    Node<T> left, right;

    public void insertToTree(T v) {
        if (value == null) {
            value = v;
            return;
        }
        if (v.compareTo(value) < 0) {
            if (left == null) {
                left = new Node<T>();
            }
            left.insertToTree(v);
        } else {
            if (right == null) {
                right = new Node<T>();
            }
            right.insertToTree(v);
        }
    }
    public Node<T> deleteFromTree(T v) {
        if (value == null) {
            return this;
        }

        if (v.compareTo(value) < 0) {
            if (left != null) {
                left = left.deleteFromTree(v);
            }
        } else if (v.compareTo(value) > 0) {
            if (right != null) {
                right = right.deleteFromTree(v);
            }
        } else {
            if (left == null && right == null) {
                return null;
            } else if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                Node<T> minNode = right;
                while (minNode.left != null) {
                    minNode = minNode.left;
                }
                this.value = minNode.value;
                right = right.deleteFromTree(minNode.value);
            }
        }
        return this;
    }

    public int height() {
        if (value == null) {
            return 0;
        }
        int leftHeight = (left == null) ? 0 : left.height();
        int rightHeight = (right == null) ? 0 : right.height();
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

}