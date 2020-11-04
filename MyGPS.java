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
import edu.princeton.cs.algs4.StdOut;

public class MyGPS {
    public static void main(String[] args) {
        In in = new In(args[0]);
        GraphPgm3 graph = GraphPgm3.construct(in);
        StdOut.println(graph);
    }
}