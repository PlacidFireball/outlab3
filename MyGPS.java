/* *****************************************************************************
 *  Name:    Jared Weiss, Jacob Clostio
 *  NetID:   t95g284,
 *  Precept: P00
 *
 *  Description:  MyGPS class, designed to find the shortest distance between
 *                two points in a graph. Implements Djikstra's algorithm to
 *                find the shortest distance. Written by Jared Weiss and Jacob
 *                Clostio
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MyGPS {

    public int[] distances;
    public EdgePgm3[] edgeTo;
    public GraphPgm3 graph;

    public MyGPS(GraphPgm3 graph) {
        this.distances = new int[graph.numVertices];
        for (int i = 0; i < distances.length; i++) distances[i] = Integer.MAX_VALUE;
        this.edgeTo = new EdgePgm3[graph.numVertices];
        for (int i = 0; i < edgeTo.length; i++) edgeTo[i] = null;
        this.graph = graph;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        GraphPgm3 graph = GraphPgm3.construct(in);
        MyGPS gps = new MyGPS(graph);
        gps.dijkstra(graph, 0, 5);
        StdOut.println(gps.distance(5));

        // I want point 1 and 2
        // graph.dijkstra(1, 2) -> dist;

    }

    // TODO: minDist(boolean[] used, int[] distances, int v) -> [a, d : 10]
    // [a, b : 30], [a, c : 25], [a, d : 10]
    // TODO: relax(Edge e, int[] distances)

    public int distance(int v) {
        return distances[v];
    }

    int minDist(int v) {
        int lowestDistance = Integer.MAX_VALUE;
        int lowestIndex = -1;
        for (EdgePgm3 e : this.graph.adjacent[v]) {
            if (e.getWeight() < lowestDistance) {
                lowestDistance = e.getWeight();
                lowestIndex = e.to();
            }
        }
        return lowestIndex;
    }

    public Iterable<EdgePgm3> path(int v) {
        Stack<EdgePgm3> path = new Stack<EdgePgm3>();
        for (EdgePgm3 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void relax(EdgePgm3 e) {
        int v = e.from(), w = e.to();
        if (distances[v] > distances[w] + e.getWeight()) {
            distances[w] = distances[v] + e.getWeight();
            edgeTo[w] = e;
        }
    }

    public int dijkstra(GraphPgm3 graph, int p1, int p2) {
        boolean[] used = new boolean[graph.numVertices];
        for (int i = 0; i < graph.numVertices; i++) {
            this.distances[i] = Integer.MAX_VALUE;
            used[i] = false;
        }
        distances[p1] = 0;
        used[p1] = true;
        int currNode = p1;
        do {
            for (EdgePgm3 e : graph.adjacent[currNode]) {
                relax(e);
                currNode = minDist(currNode);
                used[currNode] = true;
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
