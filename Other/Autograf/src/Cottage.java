import java.util.HashMap;
import java.util.HashSet;

public class Cottage {
    HashMap<GNode, HashSet<GNode>> graph;
    
    public Cottage(){
        graph = new HashMap<GNode, HashSet<GNode>>();
    }

    public boolean addGNode(GNode gnome, HashSet<GNode> buddies, boolean isUndirected){
        
        buddies.remove(gnome);
        if(!graph.keySet().containsAll(buddies)){
            return false;
        }
        graph.put(gnome, buddies);
        return false;
    }
}
