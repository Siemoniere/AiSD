import java.util.ArrayList;

public class QuickSort {

    private int n;
    private ArrayList<Integer> a;
    private int swap = 0, cmp = 0;

    public QuickSort(int n, ArrayList<Integer> a){
        this.a = a;
        this.n = n;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> a){
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
        sorted.addAll(sort(left));
        sorted.add(pivot);
        sorted.addAll(sort(right));
        if (n < 40){
            System.out.print("Scalanie: ");
            for (int val : sorted){
                System.out.printf("%d ", val);
            }
            System.out.println();
        }
        return sorted;
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