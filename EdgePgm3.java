/* *****************************************************************************
 *  Name:    Jared Weiss, Jacob Clostio
 *  NetID:   t95g284, z32t832
 *
 *  Description:  Edge helper class designed to help make a representation of a
 *                graph in Java. Written by Jared Weiss and Jacob Clostio
 *
 **************************************************************************** */

public class EdgePgm3 implements Comparable<EdgePgm3> {
    /* Variables */
    private int v, w, weight;

    /* The constructor */
    public EdgePgm3(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /* Returns v */
    public int from() {
        return v;
    }

    // Returns w
    public int to() {
        return w;
    }

    // getter
    public int getWeight() {
        return weight;
    }

    // compareTo function, compares the weights of an edge, necessary for the MinPQ
    public int compareTo(EdgePgm3 other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }

    // String representation of the Edge class
    public String toString() {
        return (this.v + 1) + " -> " + (this.w + 1);
    }
}
