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
    /* Instance variables */
    public final int numVertices;
    public int numEdges;
    public LinkedList<EdgePgm3>[] adjacent;

    /* Makes an empty graph with numVertices vertices */
    public GraphPgm3(int numVertices) {
        this.numVertices = numVertices;
        this.numEdges = 0;
        this.adjacent = (LinkedList<EdgePgm3>[]) new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++)
            this.adjacent[i] = new LinkedList<>();
    }

    /* Constructs a graph from standard input
     * Syntax:
     * - a 10 20 31 <- makes an edge from vertex 10 to vertex 20 with weight 31
     * - p sp 30 40 <- signals making a graph with 30 vertices and 40 edges
     * - c implies a comment
     * */
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

    /* Adds an edge to the graph from v to w with weight, weight*/
    public void addEdge(int v, int w, int weight) {
        this.numEdges++; // increase numEdges
        adjacent[v].addLast(new EdgePgm3(v, w, weight)); // add the edge to the list
    }

    // getter
    public int getNumEdges() {
        return numEdges;
    }

    // shorthand way of writing Integer.parseInt(String)
    private static int parse(String s) {
        return Integer.parseInt(s);
    }

    // returns a queue of all the edges in the graph
    public Iterable<EdgePgm3> edges() {
        Queue<EdgePgm3> q = new Queue<>();
        for (LinkedList<EdgePgm3> edges : adjacent) {
            for (EdgePgm3 edge : edges) q.enqueue(edge);
        }
        return q;
    }

    // string representation of the graph
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
