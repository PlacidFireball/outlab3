/* *****************************************************************************
 *  Name:    Jared Weiss, Jacob Clostio
 *  NetID:   t95g284, z32t832
 *
 *  Description:  Graph classe designed to make a representation of a graph in
 *                Java. Written by Jared Weiss and Jacob Clostio
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.LinkedList;

public class GraphPgm3 {

    public final int numVertices;
    public int numEdges;
    public LinkedList<EdgePgm3>[] adjacent;

    public GraphPgm3(int numVertices) {
        this.numVertices = numVertices;
        this.numEdges = 0;
        this.adjacent = (LinkedList<EdgePgm3>[]) new LinkedList[numVertices];
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
                graph.addEdge(parse(tokens[1]) - 1, parse(tokens[2]) - 1, parse(tokens[3]));
            }
        }
        return graph;
    }

    public void addEdge(int v, int w, int weight) {
        this.numEdges++;
        adjacent[v].addLast(new EdgePgm3(v, w, weight));
    }

    public int getNumEdges() {
        return numEdges;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

    public Iterable<EdgePgm3> edges() {
        Queue<EdgePgm3> q = new Queue<>();
        for (LinkedList<EdgePgm3> edges : adjacent) {
            for (EdgePgm3 edge : edges) q.enqueue(edge);
        }
        return q;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.adjacent.length; i++) {
            sb.append(i + ": ");
            sb.append(Arrays.toString(adjacent[i].toArray()));
            sb.append("\n");
        }
        return sb.toString();
    }
}

