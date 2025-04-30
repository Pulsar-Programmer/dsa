import java.util.HashSet;

/** this is a single territory with the connections to other territories and how many
soldiers it holds */
public class Territory {
    public String name;
    public int soldiers;
    public HashSet<Territory> connections;
}
