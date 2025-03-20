import java.util.Arrays;

public class TSP {

    static void g(int[][] cost, boolean[] visited ,int ind, int n, int count,int cstnow, int[] ans) {

        
        if (count == n && cost[ind][0] != 0) {
            ans[0] = Math.min(ans[0] ,cstnow + cost[ind][0]);
            return;
        }

        
        for (int i = 0; i < n; i++) {
            if (!visited[i] && cost[ind][i] != 0) {

                visited[i] = true;
                g(cost, visited, i, n, count + 1,cstnow + cost[ind][i],ans);
                visited[i] = false;
            }
        }
    }

    static int tsp(int[][] cost) {
        int n = cost.length;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);  

        visited[0] = true;

        int[] ans = {Integer.MAX_VALUE};  

        g(cost, visited, 0, n, 1, 0, ans);  

        return ans[0];
    }
    public static void main(String[] args) {
        int[][] cost = {
            {0, 16, 11 ,6 },
            {8, 0 ,13 , 16},
            {4, 7 ,0 , 9},
            {5, 12 ,2, 0},

        }; 
        int ans = tsp(cost);

        System.out.println(ans);
    }
}
