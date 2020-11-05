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

    /* MyGPS variables */
    private int[] distances; // contains distances from source node to a destination
    private EdgePgm3[] edgeTo; // edgeTo array for maintaining paths
    private GraphPgm3 graph; // the graph

    /* The constructor */
    public MyGPS(GraphPgm3 graph) {
        this.distances = new int[graph.numVertices];
        for (int i = 0; i < distances.length; i++) distances[i] = Integer.MAX_VALUE;
        this.edgeTo = new EdgePgm3[graph.numVertices];
        for (int i = 0; i < edgeTo.length; i++) edgeTo[i] = null;
        this.graph = graph;
    }

    public static void main(String[] args) {
        // Make a new graph and a gps module from a provided file
        In in = new In(args[0]);
        GraphPgm3 graph = GraphPgm3.construct(in);
        MyGPS gps = new MyGPS(graph);

        // Main loop
        String display = "The current graph has vertices from 1 to " + graph.numVertices +
                "\nWould you like to:\n\t1: Find a new route\n\t2: Exit";
        In input = new In();
        while (true) {
            StdOut.println(display);
            // get user input
            int userInput = input.readInt();
            if (userInput == 1) {
                StdOut.println("Please enter the source node.");
                int source = input.readInt();
                StdOut.println("Please enter a destination node.");
                int dest = input.readInt();
                long startTime = System.currentTimeMillis();
                // run dijkstra's algorithm from user input
                gps.dijkstra(source - 1, dest - 1);
                long endTime = System.currentTimeMillis();
                long elapsed = endTime - startTime;
                // display relevant data
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

    public int distance(int v) {
        return distances[v];
    }

    /* Display the path that dijkstra's algorithm finds */
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

    /* Constructs a path from the edgeTo array that the algorithm uses */
    // function used from the lecture on 11/4/2020
    public Iterable<EdgePgm3> path(int v) {
        Stack<EdgePgm3> path = new Stack<EdgePgm3>();
        for (EdgePgm3 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    /* Relaxes an edge, based on algorithm from lecture with a few tweaks */
    private void relax(EdgePgm3 e, boolean[] used, MinPQ<EdgePgm3> pq) {
        int v = e.from(), w = e.to(); // grab the vertexes
        // check if the distance of w needs to be updated
        if (distances[w] > distances[v] + e.getWeight()) {
            distances[w] = distances[v] + e.getWeight(); // update it
            edgeTo[w] = e; // change the edge for pathing
            if (!used[w]) { // if w hasn't been added to the priority queue
                used[w] = true; // mark it as added
                // and add all it's adjacent edges
                for (EdgePgm3 edge : graph.adjacent[w]) pq.insert(edge);
            }
        }
    }

    public int dijkstra(int p1, int p2) {
        // Setup
        boolean[] used = new boolean[graph.numVertices];
        for (int i = 0; i < graph.numVertices; i++) {
            this.distances[i] = Integer.MAX_VALUE;
            used[i] = false;
        }
        distances[p1] = 0;
        used[p1] = true;

        // the pq
        MinPQ<EdgePgm3> pq = new MinPQ<>();
        pq.insert(graph.adjacent[p1].getFirst());

        while (!pq.isEmpty()) {
            EdgePgm3 currEdge = pq.delMin();
            int v = currEdge.from();
            used[v] = true;
            // relax each edge that is adjacent to v
            for (EdgePgm3 edge : graph.adjacent[v]) {
                relax(edge, used, pq);
            }
        }
        // return the shortest distance to p2
        return distance(p2);
    }

}
