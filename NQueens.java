import java.util.Arrays;

public class NQueens {

    static void nQueen(int[] x, int k, int n) {
        for (int i = 0; i < n; i++) {
            if (place(x, k, i)) {
                x[k] = i;
                if (k == n - 1) {  
                    System.out.println(Arrays.toString(x));
                } else {
                    nQueen(x, k + 1, n);
                }
            }
        }
    }

    static boolean place(int[] x, int k, int i) {
        for (int j = 0; j < k; j++) { 
            if (x[j] == i || (Math.abs(x[j] - i) == Math.abs(j - k))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        int[] x = new int[n];
        nQueen(x, 0, n);
    }
}
