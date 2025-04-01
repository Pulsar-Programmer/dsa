
import java.util.HashMap;
import java.util.HashSet;

/** Hashi (this will have the graph and it’s algorithms ) */
public class Hashi {
    /**The graph of the islands and their neighbors */
    HashMap<Island, HashSet<Island>> nodes_neighbors;

    public Hashi(HashMap<Island, HashSet<Island>> nodes_neighbors) {
        this.nodes_neighbors = nodes_neighbors;
    }

    public Hashi load_from_str(String str){

        



        
        // char[] inner = new char[str.indexOf("\n")][];
        // char[][] outer = new char[0][0];
        // return load_from_2darray(array);
    }
    // public Hashi load_from_2darray(char[][] array) {
    //     return new Hashi();
    // }
    
}
