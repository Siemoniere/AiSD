import java.util.ArrayList;

public class MySort {

    private ArrayList<Integer> a;
    private int swap = 0, cmp = 0;

    public MySort(int n, ArrayList<Integer> a){
        this.a = a;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> a){
        if (a.size() <= 1) return new ArrayList<>(a);

        ArrayList<ArrayList<Integer>> runs = new ArrayList<>();
        int i = 0;
        while (i < a.size()){
            ArrayList<Integer> rune = new ArrayList<>();
            rune.add(a.get(i));
            i++;
            while (i < a.size() && rune.get(rune.size() - 1) <= a.get(i)) {
                cmp++;
                rune.add(a.get(i));
                i++;
            }
            runs.add(rune);
        }
        while (runs.size() > 1) {
            ArrayList<ArrayList<Integer>> newRuns = new ArrayList<>();
            for (int j = 0; j < runs.size(); j += 2) {
                if (j + 1 < runs.size()) {
                    newRuns.add(merge(runs.get(j), runs.get(j + 1)));
                } else {
                    newRuns.add(runs.get(j));
                }
            }
            runs = newRuns;
        }
        return runs.get(0);
    }

    public ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> sorted = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()){
            cmp++;
            if(a.get(i) > b.get(j)){
                sorted.add(b.get(j));
                j++;
            } else {
                sorted.add(a.get(i));
                i++;
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