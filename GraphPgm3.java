import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphPgm3 {
    private class Edge {
        private int w, weight;
        public Edge( int w, int weight) {
            this.w = w;
            this.weight = weight;
        }
        public String toString() {
            return this.w+" : "+this.weight;
        }
    }

    public final int numVertices;
    public int numEdges;
    public LinkedList<Edge>[] adjacent;

    public GraphPgm3(int numVertices) {
        this.numVertices = numVertices;
        this.numEdges = 0;
        this.adjacent = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++)
            this.adjacent[i] = new LinkedList<>();
    }

    public static GraphPgm3 construct(In in) {
        GraphPgm3 graph = null;
        String[] lines = in.readAllLines();
        String delimiter = " ";
        for (String line : lines) {
            String[] tokens = line.split(delimiter);
            if (line.startsWith("p")) {
                graph = new GraphPgm3(Integer.parseInt(tokens[2]));
            }
            if (line.startsWith("a") && graph != null) {
                graph.addEdge(parse(tokens[1]), parse(tokens[2]), parse(tokens[3]));
            }
        }
        return graph;
    }

    public void addEdge(int v, int w, int weight) {
        adjacent[v].addLast(new Edge(w, weight));
    }

    public int getNumEdges() {
        return numEdges;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.adjacent.length; i++) {
            sb.append(i+": ");
            sb.append(Arrays.toString(adjacent[i].toArray()));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int dijkstra(int p1, int p2) {
        boolean used[] = new boolean[this.numVertices];
        int distances[] = new int[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            distances[i] = Integer.MAX_VALUE;
            used[i] = false;
        }
        distances[p1] = 0;
        used[p1] = true;
        int currNode = p1;
        do {
            for (Edge e : adjacent[currNode]) {

            }
        } while (!allTrue(used));
        return 0;
    }

    private boolean allTrue(boolean[] bools) {
        for (int i = 0; i < bools.length; i++) {
            if (!bools[i]) return false;
        }
        return true;
    }

}

