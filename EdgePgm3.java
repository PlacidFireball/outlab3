public class EdgePgm3 {
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

    public String toString() {
        return this.v + ", " + this.w + " : " + this.weight;
    }
}
