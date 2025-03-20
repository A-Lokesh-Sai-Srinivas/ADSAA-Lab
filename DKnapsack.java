import java.util.Arrays;

public class DKnapsack {

    public static class Pair {
        double p, w;
        Pair(double p, double w) {
            this.p = p;
            this.w = w;
        }
    }

    public static int largest(Pair[] pair, double[] w, int t, int h, int i, double to) {
        int max = t;
        for (int j = t; j < h; j++) {
            if (pair[j].w + w[i] <= to && pair[j].p > pair[max].p)
                max = j;
        }
        return max;
    }

    public static void dKnapsack(double[] p, double[] w, int[] x, int n, double to) {
        Pair[] pair = new Pair[2 * n + 1];
        for (int i = 0; i < pair.length; i++) pair[i] = new Pair(0, 0);

        int[] b = new int[n + 1];
        pair[0] = new Pair(0.0, 0.0);
        int t = 0, h = 1;
        int next = 2;

        b[0] = next;

        for (int i = 0; i < n; i++) {
            int k = t;
            int u = largest(pair, w, t, h, i, to);

            for (int j = t; j <= u; j++) {
                double pp = pair[j].p + p[i];
                double ww = pair[j].w + w[i];

                if (ww <= to) {
                    if (next < pair.length) {
                        pair[next] = new Pair(pp, ww);
                        next++;
                    } else {
                        break;
                    }
                }

                while (k < h) {
                    if (next < pair.length) {
                        pair[next] = new Pair(pair[k].p, pair[k].w);
                        next++;
                    } else {
                        break;
                    }
                    k++;
                }
            }

            t = h;
            h = next - 1;
            b[i + 1] = next;
        }

        traceBack(p, w, pair, x, to, n, b);
    }

    private static void traceBack(double[] p, double[] w, Pair[] pair, int[] x, double to, int n, int[] b) {
        Arrays.fill(x, 0);

        double rw = to;
        int i = n - 1;
        while (i >= 0) {
            if (pair[b[i + 1] - 1].w <= rw) {
                x[i] = 1;
                rw -= w[i];
            }
            i--;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        double[] p = {40, 100, 50, 60};
        double[] w = {2, 3, 5, 6};
        double to = 8;
        int[] x = new int[n];

        dKnapsack(p, w, x, n, to);

        System.out.println("Selected items (1 = included, 0 = not included): ");
        System.out.println(Arrays.toString(x));
    }
}
