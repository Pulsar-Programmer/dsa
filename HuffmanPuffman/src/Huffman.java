
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/** Huffman (this will have the data sets and algorithms) */
public class Huffman {
    
    HashMap<Character, String> encoding;
    HashMap<String, Character> decoding;
    String final_message;

    public Huffman(String content){
        encoding = encoding_hashmap(create_tree(process(content)));
        final_message = encode(encoding, content);
        decoding = decoding_hashmap(encoding);
    }

    private static PriorityQueue<CharNode> process(String content){
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

    private static CharNode create_tree(PriorityQueue<CharNode> pq){
        while(pq.size() > 1){
            var left = pq.remove();
            var right = pq.remove();
            pq.add(new CharNode(left.frequency + right.frequency, left, right));
        }
        return pq.remove();
    }

    static private class NodeCodeUnion{
        CharNode node;
        String code;
        public NodeCodeUnion(CharNode node, String code){
            this.node = node;
            this.code = code;
        }
    }

    private static HashMap<Character, String> encoding_hashmap(CharNode tree){
        var hm = new HashMap<Character, String>();

        Stack<NodeCodeUnion> stack = new Stack<>();
        stack.push(new NodeCodeUnion(tree, ""));

        while(!stack.isEmpty()){
            var popped = stack.pop();
            var node = popped.node;
            var code = popped.code;

            if(node.character.isPresent()){
                hm.put(node.character.get(), code);
            } else {
                if(node.left.isPresent()){
                    stack.push(new NodeCodeUnion(node.left.get(), code + "0"));
                }
                if(node.right.isPresent()){
                    stack.push(new NodeCodeUnion(node.right.get(), code + "1"));
                }
            }
        }
            
        return hm;
    }

    private static HashMap<String, Character> decoding_hashmap(HashMap<Character, String> encoding){
        HashMap<String, Character> decoding = new HashMap<>();
        for (var entry : encoding.entrySet()) {
            decoding.put(entry.getValue(), entry.getKey());
        }
        return decoding;
    }

    public static String encode(HashMap<Character, String> encoding, String content){
        String built = "";
        for(var c : content.chars().toArray()){
            built += encoding.get((char)c);
        }
        return built;
    }

    @Override
    public String toString() {
        return encoding.toString();
    }

    public static String decode(HashMap<String, Character> decoding, String content){
        String fynel = "";
        String read = "";
        for (var c : content.toCharArray()) {
            read += c;
            if(decoding.containsKey(read)){
                fynel += read;
                read = "";
            }
        }
        return fynel;
    }
    
}
