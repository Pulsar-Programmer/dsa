/** Island (This is a single island) */
public class Island {
    int x; /// The x coordinate of the island
    int y; /// The y coordinate of the island
    /** The number of bridges that must be connected to this island */
    int size;

    public Island(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    

    @Override
    public boolean equals(Object ob) {
        var obj = (Island) ob;
        if (obj == null || this == null) {
            return obj == null ^ this == null;
        }
        return this.x == obj.x && this.y == obj.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
