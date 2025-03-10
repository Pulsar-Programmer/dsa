
import java.util.HashMap;
import java.util.PriorityQueue;

/** Huffman (this will have the data sets and algorithms) */
public class Huffman {
    
    PriorityQueue<CharNode> pq;

    public Huffman(String content){
        pq = process(content);
    }

    public static PriorityQueue<CharNode> process(String content){
        var hm = new HashMap<Character, Integer>();
        content.chars().forEach((v)->{
            if(hm.containsKey((char)v)){
                hm.put((char)v, hm.get((char)v)+1);
            }else{
                hm.put((char)v, 1);
            }
        });
        var pq = new PriorityQueue<CharNode>();
        hm.forEach((k,v)->{
            pq.add(new CharNode(k,v));
        });
        return pq;
    }

    public static void encode(){

    }

    public static void decode(){
        
    }
    
}
