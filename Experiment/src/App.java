import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

class Graph {
    private int numVertices;
    private List<List<Integer>> adj;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adj = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public List<List<Integer>> maxCliques() {
        List<List<Integer>> cliques = new ArrayList<>();
        List<Integer> R = new ArrayList<>();
        List<Integer> P = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            P.add(i);
        }
        List<Integer> X = new ArrayList<>();

        bronKerboschRecursive(cliques, R, P, X);
        return cliques;
    }

    private void bronKerboschRecursive(List<List<Integer>> cliques, List<Integer> R, List<Integer> P, List<Integer> X) {
        if (P.isEmpty() && X.isEmpty()) {
            cliques.add(new ArrayList<>(R));
            return;
        }

        List<Integer> P_copy = new ArrayList<>(P);
        for (Integer v : P_copy) {
            List<Integer> R_new = new ArrayList<>(R);
            R_new.add(v);

            List<Integer> neighbors_v = adj.get(v);

            List<Integer> P_intersect_N = new ArrayList<>(P);
            P_intersect_N.retainAll(neighbors_v);

            List<Integer> X_intersect_N = new ArrayList<>(X);
            X_intersect_N.retainAll(neighbors_v);

            bronKerboschRecursive(cliques, R_new, P_intersect_N, X_intersect_N);

            P.remove(v);
            X.add(v);
        }
    }
}
