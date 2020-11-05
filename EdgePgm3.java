/* *****************************************************************************
 *  Name:    Jared Weiss, Jacob Clostio
 *  NetID:   t95g284, z32t832
 *
 *  Description:  Edge helper class designed to help make a representation of a
 *                graph in Java. Written by Jared Weiss and Jacob Clostio
 *
 **************************************************************************** */

public class EdgePgm3 implements Comparable<EdgePgm3> {
    private int v, w, weight;

    public EdgePgm3(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(EdgePgm3 other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }

    public String toString() {
        return (this.v + 1) + " -> " + (this.w + 1);
    }
}
