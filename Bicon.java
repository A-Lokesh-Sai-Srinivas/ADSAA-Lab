import java.util.*;
import java.io.*;


public class Bicon {
    private int V ,E ;
    private LinkedList<Integer> adj[];

    static int count = 0, time =0;

    class Edge {
        int u , v;
        Edge(int u , int v ){
            this.u = u;
            this.v = v;
        }
    };

    Bicon(int v ){
        V = v;
        E = 0;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    void BCCUtil(int u, int disc[], int low[], LinkedList<Edge> st, int parent[]) {
        disc[u] = low[u] = ++time;
        int children = 0;

        Iterator<Integer> it = adj[u].iterator();
        while (it.hasNext()) {
            int v = it.next();

            if (disc[v] == -1) {
                children++;
                parent[v] = u;

                st.add(new Edge(u, v));
                BCCUtil(v, disc, low, st, parent);

                if (low[u] > low[v]) {
                    low[u] = low[v];

                }

                if ((disc[u] == 1 && children > 1) || (disc[u] > 1 && low[v] >= disc[u])) {
                    while (st.getLast().u != u || st.getLast().v != v) {
                        System.out.print(st.getLast().u +"--" + st.getLast().v + " ");
                        st.removeLast();
                    }
                    System.out.println(st.getLast().u +"--" + st.getLast().v + " ");
                    st.removeLast();

                    count++;    
                }
            }

            else if (v != parent[u] && disc[v] < disc[u] ){
                if (low[u] > disc[v]) {
                    low[u] = disc[v];
                }

                st.add(new Edge(u, v));
            }
        }
    }

    void BCC() {
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        LinkedList<Edge> st = new LinkedList<Edge>();
 
        for (int i = 0; i < V; i++) {
            disc[i] = -1;
            low[i] = -1;
            parent[i] = -1;
        }
 
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                BCCUtil(i, disc, low, st, parent);
 
            int j = 0;
 
            while (st.size() > 0) {
                j = 1;
                System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                st.removeLast();
            }
            if (j == 1) {
                System.out.println();
                count++;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the file name of the inputs: ");
        String fileName = sc.nextLine();
    
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstLine = br.readLine().trim();
            int n = Integer.parseInt(firstLine);
            System.out.println("Number of vertices: " + n);
            Bicon g = new Bicon(n);
    
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        try {
                            int u = Integer.parseInt(parts[0]);
                            int v = Integer.parseInt(parts[1]);
                            g.addEdge(u, v);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid edge format (non-integer found): " + line);
                        }
                    } else {
                        System.out.println("Invalid edge format, skipping: " + line);
                    }
                }
            }
    
            g.BCC();
            System.out.println("Above are " + count + " biconnected components in the graph.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}    