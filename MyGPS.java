/* *****************************************************************************
 *  Name:    Jared Weiss, Jacob Clostio
 *  NetID:   t95g284, z32t832
 *
 *  Description:  MyGPS class, designed to find the shortest distance between
 *                two points in a graph. Implements Djikstra's algorithm to
 *                find the shortest distance. Written by Jared Weiss and Jacob
 *                Clostio
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MyGPS {

    private int[] distances;
    public EdgePgm3[] edgeTo;
    private GraphPgm3 graph;

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

        String display = "The current graph has vertices from 1 to " + graph.numVertices +
                "\nWould you like to:\n\t1: Find a new route\n\t2: Exit";
        In input = new In();
        while (true) {
            StdOut.println(display);
            int userInput = input.readInt();
            if (userInput == 1) {
                StdOut.println("Please enter the source node.");
                int source = input.readInt();
                StdOut.println("Please enter a destination node.");
                int dest = input.readInt();
                long startTime = System.currentTimeMillis();
                gps.dijkstra(source - 1, dest - 1);
                long endTime = System.currentTimeMillis();
                long elapsed = endTime - startTime;
                if (gps.distance(dest - 1) == Integer.MAX_VALUE) {
                    StdOut.println("There is no path from " + source + " to " + (dest - 1));
                }
                else {
                    StdOut.printf("Shortest path from %d to %d:\n", source, dest);
                    gps.display(gps.path(dest - 1));
                    StdOut.printf("total distance:\t%d\n", gps.distance(dest - 1));
                }

                StdOut.println("Elapsed time: " + elapsed + "ms");
            }
            else if (userInput == 2) {
                StdOut.println("Exiting...");
                break;
            }
            else {
                StdOut.println("Bad input! Please select 1 or 2");
            }
        }


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

    public void display(Iterable<EdgePgm3> path) {
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest path: ");
        boolean first = true;
        for (EdgePgm3 edge : path) {
            if (first) {
                sb.append(edge);
                first = false;
            }
            else {
                sb.append(" -> ");
                sb.append((edge.to() + 1));
            }
        }
        StdOut.println(sb.toString());
    }

    public Iterable<EdgePgm3> path(int v) {
        Stack<EdgePgm3> path = new Stack<EdgePgm3>();
        for (EdgePgm3 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void relax(EdgePgm3 e, boolean[] used, MinPQ<EdgePgm3> pq) {
        int v = e.from(), w = e.to();
        if (distances[w] > distances[v] + e.getWeight()) {
            distances[w] = distances[v] + e.getWeight();
            edgeTo[w] = e;
            if (!used[w]) {
                used[w] = true;
                for (EdgePgm3 edge : graph.adjacent[w]) pq.insert(edge);
            }
        }
    }

    public int dijkstra(int p1, int p2) {
        boolean[] used = new boolean[graph.numVertices];
        for (int i = 0; i < graph.numVertices; i++) {
            this.distances[i] = Integer.MAX_VALUE;
            used[i] = false;
        }
        distances[p1] = 0;
        used[p1] = true;

        MinPQ<EdgePgm3> pq = new MinPQ<>();
        pq.insert(graph.adjacent[p1].getFirst());

        while (!pq.isEmpty()) {
            EdgePgm3 currEdge = pq.delMin();
            int v = currEdge.from();
            used[v] = true;
            for (EdgePgm3 edge : graph.adjacent[v]) {
                relax(edge, used, pq);
            }
        }
        return distance(p2);
    }

}
