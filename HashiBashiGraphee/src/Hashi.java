
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/** Hashi (this will have the graph and it’s algorithms ) */
public class Hashi {


    /**The graph of the islands and their neighbors */
    HashMap<Island, HashSet<IslandNum>> nodes_neighbors;

    public Hashi(HashMap<Island, HashSet<IslandNum>> nodes_neighbors) {
        this.nodes_neighbors = nodes_neighbors;
    }

    public static Hashi load_from_str(String str){
        var list = str.lines().toList();
        var hashmap = new HashMap<Island, HashSet<IslandNum>>();

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length(); j++){
                var digit = list.get(i).charAt(j);
                if(Character.isDigit(digit)){
                    Island island = new Island('0' + digit);
                    
                }
            }
        }



        
        // char[] inner = new char[str.indexOf("\n")][];
        // char[][] outer = new char[0][0];
        // return load_from_2darray(array);
    }

    private static Optional<Island> repeat_left(ArrayList<String> list, int i, int j){
        int count = 0;
        var amt = list.get(i).charAt(j - 1);
        if(Character.isDigit(amt)){
            return Optional.of(new Island(amt));
        }
        
    }
    // public Hashi load_from_2darray(char[][] array) {
    //     return new Hashi();
    // }
    
}

class IslandNum{
    int num_connections;
    Island island;
    public IslandNum(int num_connections, Island island) {
        this.num_connections = num_connections;
        this.island = island;
    }
}