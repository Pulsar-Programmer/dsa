
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

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

        for(int i_ = 0; i_ < list.size(); i_++){
            for(int j_ = 0; j_ < list.get(i_).length(); j_++){
                
                final int j = j_;
                final int i = i_;

                var digit = list.get(i).charAt(j);
                if(Character.isDigit(digit)){
                    Island island = new Island(j, i, digit - '0');
                    hashmap.computeIfAbsent(island, k -> new HashSet<>());
                    ///We explore left for signs of another isle.
                    try_get(list, i, j - 1)
                        ///If we get the isle at the proper place, we compute the number of connections and add the island. 
                        .ifPresent((c) -> {
                            var num = c == '=' ? 2 : c == '-' ? 1 : 0;
                            if(num == 0){
                                return;
                            }
                            repeat_left(list, i, j).ifPresent((isle) -> {
                                var island_num = new IslandNum(num, isle);
                                hashmap.get(island).add(island_num);
                            });
                        });
                    ///We explore right.
                    try_get(list, i, j + 1)
                        .ifPresent((c) -> {
                            var num = c == '=' ? 2 : c == '-' ? 1 : 0;
                            if(num == 0){
                                return;
                            }
                            repeat_right(list, i, j).ifPresent((isle) -> {
                                var island_num = new IslandNum(num, isle);
                                hashmap.get(island).add(island_num);
                            });
                        });
                    ///We explore up.
                    try_get(list, i - 1, j)
                        .ifPresent((c) -> {
                            var num = c == '#' ? 2 : c == '|' ? 1 : 0;
                            if(num == 0){
                                return;
                            }
                            repeat_up(list, i, j).ifPresent((isle) -> {
                                var island_num = new IslandNum(num, isle);
                                hashmap.get(island).add(island_num);
                            });
                        });
                    ///We explore down.
                    try_get(list, i + 1, j)
                        .ifPresent((c) -> {
                            var num = c == '#' ? 2 : c == '|' ? 1 : 0;
                            if(num == 0){
                                return;
                            }
                            repeat_down(list, i, j).ifPresent((isle) -> {
                                var island_num = new IslandNum(num, isle);
                                hashmap.get(island).add(island_num);
                            });
                        });
                }
            }
        }




        return new Hashi(hashmap);
    }

    public String determine_if_solved(){
        for(var entry : nodes_neighbors.entrySet()){
            var island = entry.getKey();
            var neighbors = entry.getValue();
            int total_connections = 0;
            for(var neighbor : neighbors){
                total_connections += neighbor.num_connections;
            }
            if(total_connections != island.size){
                return "Not Solved: Issue with Island x=" + island.x + ", y=" + island.y;
            }
        }
        if(!cancerize_find()){
            return "Not Solved: Graph is not connected!";
        }
        return "Solved";
    }

    ///Determines if the graph is connected by giving a connected part cancer and checking if the any other part has it.
    public boolean cancerize_find(){
        ///We get the first island and its neighbors.
        if(nodes_neighbors.isEmpty()){
            return true;
        }
        ///We get ready for traversal.
        var entry = nodes_neighbors.entrySet().iterator().next();
        var island = entry.getKey();
        var unvisited = new ArrayList<Island>(entry.getValue().stream().map((island_num) -> island_num.island).toList());
        var visited = new HashSet<Island>();
        visited.add(island);
        while(!unvisited.isEmpty()){
            var unexplored = unvisited.remove(unvisited.size() - 1);
            nodes_neighbors.get(unexplored).forEach((island_num) -> {
                var neighbor = island_num.island;
                if(!visited.contains(neighbor)){
                    unvisited.add(neighbor);
                }
            });
            visited.add(unexplored);
        }
        ///We remove all the nodes that we've seen.
        for (var isle : visited) {
            nodes_neighbors.remove(isle);
        }
        ///If there is still more, it is not connected.
        return nodes_neighbors.isEmpty();
    }



    private static Optional<Character> try_get(List<String> list, int i, int j){
        Character amt;
        try {
            amt = list.get(i).charAt(j);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(amt);
    }

    private static Optional<Island> repeat_left(List<String> list, int i, int j){
        return repeat_offset(list, i, j, 0, -1);
    }
    private static Optional<Island> repeat_right(List<String> list, int i, int j){
        return repeat_offset(list, i, j, 0, 1);
    }
    private static Optional<Island> repeat_up(List<String> list, int i, int j){
        return repeat_offset(list, i, j, -1, 0);
    }
    private static Optional<Island> repeat_down(List<String> list, int i, int j){
        return repeat_offset(list, i, j, 1, 0);
    }
    private static Optional<Island> repeat_offset(List<String> list, int i, int j, int i_, int j_){
        char amt;
        try {
            amt = list.get(i + i_).charAt(j + j_);
        } catch (Exception e) {
            return Optional.empty();
        }
        if(Character.isDigit(amt)){
            return Optional.of(new Island(j + j_, i + i_, amt));
        }
        return repeat_offset(list, i + i_, j + j_, i_, j_);
    }

    @Override
    public String toString() {
        var str = new String();
        for (var entry : nodes_neighbors.entrySet()) {
            str += entry.getKey() + " -> " + entry.getValue();
            str += "\n";
        }
        return str;
    }
}

class IslandNum{
    int num_connections;
    Island island;
    public IslandNum(int num_connections, Island island) {
        this.num_connections = num_connections;
        this.island = island;
    }
    @Override
    public String toString() {
        return island.toString();
    }
}