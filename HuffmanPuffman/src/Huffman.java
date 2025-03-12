
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/** Huffman (this will have the data sets and algorithms) */
public class Huffman {
    
    HashMap<Character, String> encoding;
    String final_message;

    public Huffman(String content){
        encoding = encoding_hashmap(create_tree(process(content)));
        final_message = encode(encoding, content);
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

    public static CharNode create_tree(PriorityQueue<CharNode> pq){
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

    public static HashMap<Character, String> encoding_hashmap(CharNode tree){
        var hm = new HashMap<Character, String>();

        CharNode runner;
        String code;
        Stack<NodeCodeUnion> stack = new Stack<>();
        stack.push(new NodeCodeUnion(tree, ""));
        // System.out.println(tree);
        while(!stack.isEmpty()){
            var popped = stack.pop();
            runner = popped.node;
            code = popped.code;
            if(runner.character.isPresent()){
                hm.put(runner.character.get(), code);
                continue;
            }
            while(runner.left.isPresent()){
                runner = runner.left.get();
                final var right_code = code + "1";
                code += "0";
                if(runner.character.isPresent()){
                    hm.put(runner.character.get(), code);
                }
                if(runner.right.isPresent()){
                    stack.push(new NodeCodeUnion(runner.right.get(), right_code));
                }
            }
        }
            
        return hm;
    }

    // public static HashMap<Character, String> get_decoding(HashMap<String, Character> encoding){

    // }

    public static String encode(HashMap<Character, String> encoding, String content){
        String built = "";
        for(var c : content.chars().toArray()){
            built += encoding.get((char)c);
        }
        return built;
    }

    public static void decode(HashMap<String, Character> decoding, String content){
        
    }
    
}
