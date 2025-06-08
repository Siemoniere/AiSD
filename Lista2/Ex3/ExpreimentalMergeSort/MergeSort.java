import java.util.ArrayList;

public class MergeSort {

    private ArrayList<Integer> a;
    private int swap = 0, cmp = 0;

    public MergeSort(int n, ArrayList<Integer> a){
        this.a = a;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> a){
        if (a.size() <= 1) return new ArrayList<>(a);

        int mid = a.size() / 2;
        ArrayList<Integer> left = new ArrayList<>(a.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(a.subList(mid, a.size()));

        ArrayList<Integer> sortedLeft = sort(left);
        ArrayList<Integer> sortedRight = sort(right);

        return merge(sortedLeft, sortedRight);
    }

    public ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> sorted = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()){
            cmp++;
            if(a.get(i) <= b.get(j)){
                sorted.add(a.get(i));
                i++;
            } else {
                sorted.add(b.get(j));
                j++;
            }
            swap++;
        }
        while (i < a.size()){
            sorted.add(a.get(i));
            i++;
            swap++;
        }
        while (j < b.size()){
            sorted.add(b.get(j));
            j++;
            swap++;
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
