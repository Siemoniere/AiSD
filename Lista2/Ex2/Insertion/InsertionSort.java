public class InsertionSort {

    private int n;
    private int[] a = new int[n];
    private int swap = 0, cmp = 0;

    public InsertionSort(int n, int[] a){
        this.a = a;
        this.n = n;
    }

    public void sort(){
        if (n < 40) System.out.println("Istotne punkty: ");
        for (int j = 1; j < n; j++){
            int key = a[j];
            int i = j - 1;
            while (i >= 0) {
                cmp++;
                if (a[i] > key) {
                    a[i + 1] = a[i];
                    i--;
                    swap++;
                } else {
                    break;
                }
            }
            a[i + 1] = key;
            if (n < 40) display(a);
        }
    }

    public void display(int[] a){
        for (int i = 0; i < n; i++){
            System.out.printf("%d ", a[i]);
        }
        System.out.printf("\n");
    }
    
    public void saveOld(int[] old){
        for (int i = 0; i < n; i++){
            old[i] = a[i];
        }
    }

    public int getSwap(){
        return swap;
    }
    public int getCmp(){
        return cmp;
    }
    public boolean check(){
        for (int i = 1; i < n; i++){
            if(a[i - 1] > a[i]){
                return false;
            }
        } return true;
    }
}