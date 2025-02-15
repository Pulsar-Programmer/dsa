import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class TNode {
    private String value;
    private boolean isKey;
    private HashMap<String, TNode> next;

    public TNode(){
        this.value = "";
        isKey = false;
        next = new HashMap<>();
    }

    /** Adds a Key to the Trie. */
    public void addKey(String key){
        var runner = this;
        for(Character c : key.toCharArray()){
            runner = runner.add_or_get(c);
        }
        runner.isKey = true;
    }

    /** Gets a Node based on the given String. May not exist. */
    public Optional<TNode> getNode(String key){
        var runner = this;
        for(Character c : key.toCharArray()){
            runner = runner.next.get(c.toString());
            if(runner == null){
                return Optional.empty();
            }
        }
        return Optional.of(runner);
    }

    /** Checks if the Key is present in the Trie. */
    public boolean isKey(String key){
        var runner = this;
        for(Character c : key.toCharArray()){
            runner = runner.add_or_get(c);
        }
        return runner.isKey;
    }

    /** Deletes an existing Key of the Trie */
    public void deleteKey(String key){
        ///We create a runner to go to the end and a soonest to keep track of the soonest key/branch before we need to remove others.
        TNode runner = this;
        TNode soonest = this;
        ///We get the desired node and if it does not exist just return.
        var desired = getNode(key);
        if(desired.isEmpty()) return;
        ///If the node is at the end, then we run to the end and find the last place to put it.
        ///If the node is not at the end, then we just set it to null simply.
        if(desired.get().next.isEmpty()){
            Character last_c = key.charAt(0);
            for(Character c : key.toCharArray()){
                if(runner.next.size() > 1 || runner.isKey){
                    soonest = runner;
                    last_c = c;
                }
                runner = runner.next.get(c.toString());
                if(runner == null){
                    return;
                }
            }
            soonest.next.remove(last_c.toString());
        } else {
            desired.get().isKey = false;
        }
    }

    /** Gets a Node or Creates it if it Doesn't Exist */
    private TNode add_or_get(Character c){
        if (next.containsKey(c.toString())){
            return next.get(c.toString());
        } else {
            TNode created = new TNode();
            next.put(c.toString(), created);
            return created;
        }
    }

    /** Compresses the tree into perfect Strings by taking any one-node characters and merging them to one unit. */
    public void compress(){
        if(next.size() == 1){
            var next_next = next.values().iterator().next();
            next_next.compress();
            value = value.concat(next_next.value);
            next = next_next.next;
            compress();
        } else {
            for(TNode n : next.values()){
                n.compress();
            }
        }
    }

    // /** Searches for all possible Keys based on the first starter words. */
    // public ArrayList<String> auto(String given){
    //     ArrayList<String> words = new ArrayList<>();
    //     var val = getNode(given);
    //     if(val.isEmpty()){

    //         return words;
    //     }
    //     val.get().next.values().forEach((node) -> {
    //         for (String next_char : node.next.keySet()) {
    //             for (String composed : auto(given + next_char)) {
    //                 words.add(given + next_char + composed);
    //             }
    //         }
    //     });
    //     return words;
    // }

    /** Searches for all possible Keys based on the first starter words. */
    public ArrayList<String> auto(String given){
        ArrayList<String> words = new ArrayList<>();
        var val = getNode(given);
        if(val.isEmpty()){
            words.add("");
            return words;
        }
        for (var next_node : val.get().next.entrySet()) {
            var next_char = next_node.getKey();
            if(next_node.getValue().isKey){
                words.add(given + next_char);
            }
            for (String composed : next_node.getValue().auto("")) {
                words.add(given + next_char + composed);
            }
        }
        return words;
    }

    @Override
    public String toString() {
        return value + "->" + next;
    }



}
