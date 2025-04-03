import java.util.ArrayList;

public class DualPivot {

    private int n;
    private ArrayList<Integer> a;
    private int swap = 0, cmp = 0;

    public DualPivot(int n, ArrayList<Integer> a){
        this.a = a;
        this.n = n;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> a){
        if (a.size() <= 1) return new ArrayList<>(a);

        int p = a.get(0);
        int q = a.get(a.size() - 1);
        if (p > q){
            int temp = p;
            p = q;
            q = temp;
        }
        ArrayList<Integer> s = new ArrayList<>();
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Integer> m = new ArrayList<>();
        if (n < 40){
            System.out.print("Podział: ");
            for (int val : a) {
                System.out.printf("%d ", val);
            }
            System.out.printf(" | Pivot: %d %d\n", p, q);
        }

        for (int i = 1; i < a.size() - 1; i++) {
            cmp++;

            if(l.size() > s.size()){
                cmp++;
                if(a.get(i) > q){
                    l.add(a.get(i));
                    swap++;
                } else if (a.get(i) > p){
                    m.add(a.get(i));
                    cmp++;
                } else {
                    s.add(a.get(i));
                    swap++;
                    cmp++;
                }
            }  else {
                if(a.get(i) < p){
                    s.add(a.get(i));
                    swap++;
                } else if (a.get(i) < q){
                    m.add(a.get(i));
                    cmp++;
                } else {
                    l.add(a.get(i));
                    swap++;
                    cmp++;
                }
            }
        }

        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.addAll(sort(s));
        sorted.add(p);
        sorted.addAll(sort(m));
        sorted.add(q);
        sorted.addAll(sort(l));
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