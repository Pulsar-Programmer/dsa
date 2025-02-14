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
        TNode runner = this;
        TNode soonest = this;
        Character last_c = key.charAt(0);
        for(Character c : key.toCharArray()){
            //should it be >1 or ==1?
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
    public ArrayList<String> auto(String given){
        ArrayList<String> words = new ArrayList<>();
        var val = getNode(given);
        if(val.isEmpty()){
            return words;
        }
        val.get().next.values().forEach((node) -> {
            words.addAll(node.auto("").stream().map((word) -> given + word).toList());
        });  
        return words;
    }

    @Override
    public String toString() {
        String total = new String();
        total += value + " -> ";
        for(TNode n : next.values()){
            total += n.toString() + " \n";
        }

        return value+"->"+next;
    }



}
