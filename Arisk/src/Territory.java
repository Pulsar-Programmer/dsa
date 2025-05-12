
/** this is a single territory with the connections to other territories and how many
soldiers it holds */
public class Territory {
    public String name;
    // public HashSet<Territory> connections;
    //soldiers
    public Territory previous;

    public Territory(String name){
        this.name = name;
        // this.connections = new HashSet<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Territory)) return false;
        Territory other = (Territory) o;
        return this.name.equals(other.name);
    }

    
}

/** Boilerplate Territory^2 in place of Tuple. */
class TerritoryTerritory{
    String one, two;
    public static TerritoryTerritory struct(String one, String two){
        TerritoryTerritory territory = new TerritoryTerritory();
        territory.one = one;
        territory.two = two;
        return territory;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((one == null) ? 0 : one.hashCode());
        result = prime * result + ((two == null) ? 0 : two.hashCode());
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
        TerritoryTerritory other = (TerritoryTerritory) obj;
        if (one == null) {
            if (other.one != null)
                return false;
        } else if (!one.equals(other.one))
            return false;
        if (two == null) {
            if (other.two != null)
                return false;
        } else if (!two.equals(other.two))
            return false;
        return true;
    }
}