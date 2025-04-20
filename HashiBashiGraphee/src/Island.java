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
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Island other = (Island) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
