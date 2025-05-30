//Splay Tree
import java.io.OutputStreamWriter;
import java.io.IOException;

class Node {
	int data; // holds the key
	Node parent; // pointer to the parent
	Node left; // pointer to left child
	Node right; // pointer to right child

	public Node(int data) {
		this.data = data;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}
public class SplayTree {
	private Node root;
	public static int cmp = 0, point = 0;
	public SplayTree() {
		root = null;
	}

	private void deleteNodeHelper(Node node, int key) {
		Node x = null;
		Node t = null; 
		Node s = null;
		while (node != null){
			cmp++;
			if (node.data == key) {
				x = node;
			}
			cmp++;
			if (node.data <= key) {
				point++;
				node = node.right;
			} else {
				point++;
				node = node.left;
			}
		}

		if (x == null) {
			System.out.println("Couldn't find key in the tree");
			return;
		}
		// split operation
		splay(x);
		point++;
		if (x.right != null) {
			point++;
			t = x.right;
			point++;
			t.parent = null;
		} else {
			t = null;
		}
		s = x;
		point++;
		s.right = null;
		x = null;

		// join operation
		point++;
		if (s.left != null){ // remove x
			point++;
			s.left.parent = null;
		}
		root = join(s.left, t);
		s = null;
	}

	// rotate left at node x
	private void leftRotate(Node x) {
		point++;
		Node y = x.right;
		x.right = y.left;
		point++;
		if (y.left != null) {
			point++;
			y.left.parent = x;
		}
		point++;
		y.parent = x.parent;
		point++;
		if (x.parent == null) {
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
		point++;
		y.left = x;
		point++;
		x.parent = y;
	}

	// rotate right at node x
	private void rightRotate(Node x) {
		Node y = x.left;
		point++;
		x.left = y.right;
		point++;
		if (y.right != null) {
			point++;
			y.right.parent = x;
		}
		point++;
		y.parent = x.parent;
		point++;
		if (x.parent == null) {
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

	// Splaying operation. It moves x to the root of the tree
	private void splay(Node x) {
		while (x.parent != null) {
			point++;
			if (x.parent.parent == null) {
				point++;
				if (x == x.parent.left) {
					// zig rotation
					rightRotate(x.parent);
				} else {
					// zag rotation
					leftRotate(x.parent);
				}
			} else if (x == x.parent.left && x.parent == x.parent.parent.left) {
				point++;
				// zig-zig rotation
				rightRotate(x.parent.parent);
				rightRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.right) {
				point++;
				// zag-zag rotation
				leftRotate(x.parent.parent);
				leftRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.left) {
				point++;
				// zig-zag rotation
				leftRotate(x.parent);
				rightRotate(x.parent);
			} else {
				point++;
				// zag-zig rotation
				rightRotate(x.parent);
				leftRotate(x.parent);
			}
			point++;
		}
	}

	// joins two trees s and t
	private Node join(Node s, Node t){
		if (s == null) {
			return t;
		}

		if (t == null) {
			return s;
		}
		Node x = maximum(s);
		splay(x);
		point++;
		x.right = t;
		point++;
		t.parent = x;
		return x;
	}


	private void preOrderHelper(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		} 
	}

	private void inOrderHelper(Node node) {
		if (node != null) {
			inOrderHelper(node.left);
			System.out.print(node.data + " ");
			inOrderHelper(node.right);
		} 
	}

	private void postOrderHelper(Node node) {
		if (node != null) {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(node.data + " ");
		} 
	}

	// Pre-Order traversal
	// Node->Left Subtree->Right Subtree
	public void preorder() {
		preOrderHelper(this.root);
	}

	// In-Order traversal
	// Left Subtree -> Node -> Right Subtree
	public void inorder() {
		inOrderHelper(this.root);
	}

	// Post-Order traversal
	// Left Subtree -> Right Subtree -> Node
	public void postorder() {
		postOrderHelper(this.root);
	}

	// find the node with the minimum key
	public Node minimum(Node node) {
		point++;
		while (node.left != null) {
			point++;
			node = node.left;
		}
		return node;
	}

	// find the node with the maximum key
	public Node maximum(Node node) {
		point++;
		while (node.right != null) {
			node = node.right;
			point++;
		}
		return node;
	}

	// find the successor of a given node
	public Node successor(Node x) {
		// if the right subtree is not null,
		// the successor is the leftmost node in the
		// right subtree
		point++;
		if (x.right != null) {
			return minimum(x.right);
		}

		// else it is the lowest ancestor of x whose
		// left child is also an ancestor of x.
		Node y = x.parent;
		point++;
		while (y != null && x == y.right) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	// find the predecessor of a given node
	public Node predecessor(Node x) {
		// if the left subtree is not null,
		// the predecessor is the rightmost node in the 
		// left subtree
		point++;
		if (x.left != null) {
			return maximum(x.left);
		}

		Node y = x.parent;
		point++;
		while (y != null && x == y.left) {
			x = y;
			y = y.parent;
		}

		return y;
	}

	// insert the key to the tree in its appropriate position
	public void insert(int key) {
		Node node = new Node(key);
		Node y = null;
		Node x = this.root;

		while (x != null) {
			y = x;
			cmp++;
			if (node.data < x.data) {
				point++;
				x = x.left;
			} else {
				point++;
				x = x.right;
			}
		}

		// y is parent of x
		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.data < y.data) {
			cmp++;
			point++;
			y.left = node;
		} else {
			cmp++;
			point++;
			y.right = node;
		}

		// splay node
		splay(node);
	}

	// delete the node from the tree
	void deleteNode(int data) {
		deleteNodeHelper(this.root, data);
	}

	public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree(OutputStreamWriter out) throws IOException {
        printTreeHelper(root, out, "", true);
    }

    private void printTreeHelper(Node node, OutputStreamWriter out, String indent, boolean isRight) throws IOException {
        if (node == null) return;

        printTreeHelper(node.right, out, indent + (isRight ? "        " : " |      "), true);

        out.write(indent);
        out.write(isRight ? " /" : " \\");
        out.write("----- ");
        out.write(Integer.toString(node.data));
        out.write('\n');

        printTreeHelper(node.left, out, indent + (isRight ? " |      " : "        "), false);
    }
}