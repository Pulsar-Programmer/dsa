import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/** Huffman (this will have the data sets and algorithms) */
public class Huffman {
    
    HashMap<Character, String> encoding;
    HashMap<String, Character> decoding;
    String final_message;

    /** Naturally creates an instance for this class by encoding the given content. */
    public Huffman(String content){
        encoding = encoding_hashmap(create_tree(process(content)));
        final_message = encode(encoding, content);
        decoding = decoding_hashmap(encoding);
    }

    /** Processes the PriorityQueue ordering from the content given. */
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

    /** Creates one big tree from the Priority Queue in previous steps. */
    private static CharNode create_tree(PriorityQueue<CharNode> pq){
        while(pq.size() > 1){
            var left = pq.remove();
            var right = pq.remove();
            pq.add(new CharNode(left.frequency + right.frequency, left, right));
        }
        return pq.remove();
    }

    /** A tuple of type {@code (CharNode, String)}. */
    static private class NodeCodeUnion{
        CharNode node;
        String code;
        public NodeCodeUnion(CharNode node, String code){
            this.node = node;
            this.code = code;
        }
    }

    /**Creates the encoding hashmap given the one big CharNode tree. */
    private static HashMap<Character, String> encoding_hashmap(CharNode tree){
        ///We create a hashmap to store the final value in.
        var hm = new HashMap<Character, String>();

        ///We create a stack.
        Stack<NodeCodeUnion> unexplored = new Stack<>();
        unexplored.push(new NodeCodeUnion(tree, ""));

        ///We keep moving as long as the stack has content to explore.
        while(!unexplored.isEmpty()){
            var popped = unexplored.pop();
            var node = popped.node;
            var code = popped.code;

            ///We check if it is a leaf and treat it appropriately. Otherwise, we keep exploring undiscovered areas of the tree.
            if(node.character.isPresent()){
                hm.put(node.character.get(), code);
            } else {
                if(node.left.isPresent()){
                    unexplored.push(new NodeCodeUnion(node.left.get(), code + "0"));
                }
                if(node.right.isPresent()){
                    unexplored.push(new NodeCodeUnion(node.right.get(), code + "1"));
                }
            }
        }
            
        return hm;
    }

    /** Creates the decoding HashMap given the Encoding HashMap. */
    private static HashMap<String, Character> decoding_hashmap(HashMap<Character, String> encoding){
        HashMap<String, Character> decoding = new HashMap<>();
        for (var entry : encoding.entrySet()) {
            decoding.put(entry.getValue(), entry.getKey());
        }
        return decoding;
    }

    /** Encodes a message given the encoding and its content. */
    public static String encode(HashMap<Character, String> encoding, String content){
        String built = "";
        for(var c : content.chars().toArray()){
            built += encoding.get((char)c);
        }
        return built;
    }

    
    

    /** Decodes a message given its decoding and content. */
    /** Returns None if a String cannot be synthesized properly. */
    public static Result<String, String> decode(HashMap<String, Character> decoding, String content){
        String fynel = "";
        String read = "";
        for (var c : content.toCharArray()) {
            read += c;
            System.out.println(read);
            if(decoding.containsKey(read)){
                System.out.println("Contained!");
                fynel += decoding.get(read);
                read = "";
            }
        }
        if(!read.isEmpty()){
            return Result.Err(fynel+read);
        }
        return Result.Ok(fynel);
    }

    /**Synthesizes escape sequences from commonly escaped characters. */
    public static String unescaped(String input){
        return input.replace("\n", "\\n")
                    .replace("\t", "\\t")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\r", "\\r")
                    .replace("\'", "\\\'")
                    .replace("\"", "\\\"")
                    .replace("\\", "\\\\");
    }

    @Override
    public String toString() {
        return Huffman.unescaped(encoding.toString());
    }
    
}






/** Result from Rust */
class Result<T, E> {

    private final T value;
    private final E error;

    private Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    /** Ok Variant */
    public static <T, E> Result<T, E> Ok(T value) {
        return new Result<>(value, null);
    }

    /** Err Variant */
    public static <T, E> Result<T, E> Err(E error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return value != null;
    }

    public boolean isFailure() {
        return error != null;
    }

    public T getValue() {
        if (isFailure()) throw new IllegalStateException("No value present");
        return value;
    }

    public E getError() {
        if (isSuccess()) throw new IllegalStateException("No error present");
        return error;
    }

    @Override
    public String toString() {
        return isSuccess() ? "Ok(" + value + ")" : "Err(" + error + ")";
    }
}