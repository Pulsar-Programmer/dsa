import java.util.HashSet;

/** this is a single territory with the connections to other territories and how many
soldiers it holds */
public class Territory {
    public String name;
    public int soldiers;
    public HashSet<Territory> connections;
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
}