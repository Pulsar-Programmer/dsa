import java.util.HashMap;
import java.util.Optional;

public class TNode {
    private String value;
    private boolean isKey;
    private HashMap<String, TNode> next;

    /** Adds a Key to the Trie. */
    public void addKey(String key){
        var runner = this;
        for(Character c : key.toCharArray()){
            runner = runner.add_or_get(c);
        }
        runner.isKey = true;
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
        var runner = this;
        Optional<TNode> soonest = Optional.empty();
        for(Character c : key.toCharArray()){
            if(runner.next.size() == 1 || runner.isKey){
                soonest = Optional.of(runner);
            }
            runner = runner.next.get(c.toString());
            if(runner == null){
                return;
            }
        }
        if(soonest.isPresent()){
            soonest.get().next.remove(key.substring(key.length()-1));
        }
        // runner.isKey = false;
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
            var next_1 = next.values().iterator().next();
            var next_2hm = next_1.next;
            value += next_1.value;
            next = next_2hm;
            compress();
        } else {
            for(TNode n : next.values()){
                n.compress();
            }
        }
    }

    /** Searches for all possible Keys based on the first starter words. */
    // public void auto(){

    // }

    // @Override
    // public String toString() {
    //     String total = new String();


    //     return value+"->"+next;
    // }



}
