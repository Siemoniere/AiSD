/**
 * Small-scale testing of the Binomial Heap implementation 
 * 
 * Lily Xu
 * January 2017
 */

public class SmallTest {
	/**
	 * Display items in heap collection
	 */
	public static void printHeap(BinomialHeap h) {
		System.out.println();
		System.out.println(h);
		BinomialTree pointer = h.getPointer();
		BinomialTree t = pointer;
		do {
			System.out.println(t);
			t = t.getRight();
		} while(t != pointer);
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		// demonstrating makeBinomialHeap()
		BinomialHeap h1 = new BinomialHeap();
		
		
		// demonstrating insert()
		BinomialTree temp = null;
		for (int i = 1; i < 30; i++) {
			h1.insert(i);
			printHeap(h1);
		}
		for (int i = 1; i < 30; i++) {
			temp = h1.deleteMin();
			printHeap(h1);
		}
		

	}
}