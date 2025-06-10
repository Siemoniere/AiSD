// Binomial Heap implementation in Java

class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent, child, sibling;

    BinomialHeapNode(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = this.child = this.sibling = null;
    }
}

public class BH {
    private BinomialHeapNode head;
    public static int cmp = 0;

    public BH() {
        head = null;
    }

    // Merge two binomial trees of same degree
    private BinomialHeapNode mergeTrees(BinomialHeapNode b1, BinomialHeapNode b2) {
        cmp++;
        if (b1.key > b2.key) {
            BinomialHeapNode temp = b1;
            b1 = b2;
            b2 = temp;
        }
        b2.parent = b1;
        b2.sibling = b1.child;
        b1.child = b2;
        b1.degree++;
        return b1;
    }

    // Merge two binomial heaps
    private BinomialHeapNode merge(BinomialHeapNode h1, BinomialHeapNode h2) { 
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        BinomialHeapNode head, tail;
        cmp++;
        if (h1.degree <= h2.degree) {
            head = h1;
            h1 = h1.sibling;
        } else {
            head = h2;
            h2 = h2.sibling;
        }
        tail = head;

        while (h1 != null && h2 != null) {
            cmp++;
            if (h1.degree <= h2.degree) {
                tail.sibling = h1;
                h1 = h1.sibling;
            } else {
                tail.sibling = h2;
                h2 = h2.sibling;
            }
            tail = tail.sibling;
        }
        tail.sibling = (h1 != null) ? h1 : h2;
        return head;
    }

    // Union operation
    public BinomialHeapNode union(BinomialHeapNode h1, BinomialHeapNode h2) {
        BinomialHeapNode newHead = merge(h1, h2);
        if (newHead == null) return null;

        BinomialHeapNode prev = null, curr = newHead, next = curr.sibling;

        while (next != null) {
            cmp+= 2;
            if (curr.degree != next.degree ||
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                cmp++;
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    curr = mergeTrees(curr, next);
                } else {
                    if (prev == null) {
                        newHead = next;
                    } else {
                        prev.sibling = next;
                    }
                    curr = mergeTrees(next, curr);
                }
            }
            next = curr.sibling;
        }
        return newHead;
    }

    public void insert(int key) {
        BinomialHeapNode node = new BinomialHeapNode(key);
        head = union(head, node);
    }

    public int getMin() {
        if (head == null) throw new IllegalStateException("Heap is empty");
        BinomialHeapNode y = null, x = head;
        int min = Integer.MAX_VALUE;
        while (x != null) {
            cmp++;
            if (x.key < min) {
                min = x.key;
                y = x;
            }
            x = x.sibling;
        }
        return y.key;
    }

    public int extractMin() {
        if (head == null) throw new IllegalStateException("Heap is empty");
        BinomialHeapNode minNode = head, minPrev = null, prev = null, curr = head;
        int min = head.key;

        while (curr != null) {
            cmp++;
            if (curr.key < min) {
                min = curr.key;
                minPrev = prev;
                minNode = curr;
            }
            prev = curr;
            curr = curr.sibling;
        }

        // Remove minNode from root list
        if (minPrev != null) {
            minPrev.sibling = minNode.sibling;
        } else {
            head = minNode.sibling;
        }

        // Reverse minNode's children and union with heap
        BinomialHeapNode child = minNode.child;
        BinomialHeapNode revChild = null;
        while (child != null) {
            BinomialHeapNode next = child.sibling;
            child.sibling = revChild;
            child.parent = null;
            revChild = child;
            child = next;
        }
        head = union(head, revChild);
        return minNode.key;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // For testing: print heap
    public void print() {
        BinomialHeapNode curr = head;
        while (curr != null) {
            System.out.print("B" + curr.degree + ": ");
            printTree(curr);
            System.out.println();
            curr = curr.sibling;
        }
    }

    private void printTree(BinomialHeapNode node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        printTree(node.child);
        printTree(node.sibling);
    }
    //getters and setters
    public BinomialHeapNode getHead() {
        return head;
    }
    public void setHead(BinomialHeapNode node) {
        this.head = node;
    }
}