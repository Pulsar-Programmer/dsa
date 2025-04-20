
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
                    //explore left
                    //explore right
                    //explore down
                    //explore up

                }
            }
        }




        return new Hashi(hashmap);
    }





    private static Optional<Character> try_get(ArrayList<String> list, int i, int j){
        Character amt;
        try {
            amt = list.get(i).charAt(j);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(amt);
    }


    private static Optional<Island> repeat_left(ArrayList<String> list, int i, int j){
        return repeat_offset(list, i, j, 0, -1);
    }
    private static Optional<Island> repeat_right(ArrayList<String> list, int i, int j){
        return repeat_offset(list, i, j, 0, 1);
    }
    private static Optional<Island> repeat_up(ArrayList<String> list, int i, int j){
        return repeat_offset(list, i, j, -1, 0);
    }
    private static Optional<Island> repeat_down(ArrayList<String> list, int i, int j){
        return repeat_offset(list, i, j, 1, 0);
    }
    private static Optional<Island> repeat_offset(ArrayList<String> list, int i, int j, int i_, int j_){
        char amt;
        try {
            amt = list.get(i + i_).charAt(j + j_);
        } catch (Exception e) {
            return Optional.empty();
        }
        if(Character.isDigit(amt)){
            return Optional.of(new Island(amt));
        }
        return repeat_offset(list, i + i_, j + j_, i_, j_);
    }

    @Override
    public String toString() {
        var str = new String();
        for (var entry : nodes_neighbors.entrySet()) {
            str += entry.getKey() + " -> " + entry.getValue();
        }
        return str;
    }
}

class IslandNum{
    boolean num_connections;
    Island island;
    public IslandNum(boolean num_connections, Island island) {
        this.num_connections = num_connections;
        this.island = island;
    }
}