import java.util.ArrayList;

public class Hybrid {

    private int n;
    private ArrayList<Integer> a;
    private int swap = 0, cmp = 0;

    public Hybrid(int n, ArrayList<Integer> a){
        this.a = a;
        this.n = n;
    }

    public ArrayList<Integer> quickSort(ArrayList<Integer> a, int threshold){
        if (a.size() <= 1) return new ArrayList<>(a);

        int pivot = a.get(0);
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        if (n < 40){
            System.out.print("Podział: ");
            for (int val : a) {
                System.out.printf("%d ", val);
            }
            System.out.printf(" | Pivot: %d\n", pivot);
        }

        for (int i = 1; i < a.size(); i++) {
            cmp++;
            if (a.get(i) <= pivot) {
                left.add(a.get(i));
                swap++;
            } else {
                right.add(a.get(i));
            }
        }

        ArrayList<Integer> sorted = new ArrayList<>();
        if(a.size() > threshold){
            sorted.addAll(quickSort(left, threshold));
            sorted.add(pivot);
            sorted.addAll(quickSort(right, threshold));
        } else {
            sorted.addAll(insertionSort(left));
            sorted.add(pivot);
            sorted.addAll(insertionSort(right));
        }
        if (n < 40){
            System.out.print("Scalanie: ");
            for (int val : sorted){
                System.out.printf("%d ", val);
            }
            System.out.println();
        }
        return sorted;
    }
    public ArrayList<Integer> insertionSort(ArrayList<Integer> a){
        for (int j = 1; j < a.size(); j++){
            int key = a.get(j);
            int i = j - 1;
            while (i >= 0 && a.get(i) > key){
                cmp++;
                a.set(i + 1, a.get(i));
                i--;
                swap++;
            }
            cmp++;
            a.set(i + 1, key);
            if (n < 40) display(a);
        }
        return a;
    }

    public void display(ArrayList<Integer> a) {
        for (int val : a) {
            System.out.printf("%d ", val);
        }
        System.out.println();
    }
    
    public void saveOld(ArrayList<Integer> old){
        old.clear();
        old.addAll(this.a);
    }

    public int getSwap(){
        return swap;
    }
    public int getCmp(){
        return cmp;
    }
    public void setSorted(ArrayList<Integer> sorted){
        this.a.clear();
        this.a.addAll(sorted);
    }
    public boolean check(){
        for (int i = 1; i < a.size(); i++){
            if(a.get(i - 1) > a.get(i)){
                return false;
            }
        } return true;
    }
}