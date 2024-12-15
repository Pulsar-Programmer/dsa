import java.util.HashMap;
import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        


        var map = new HashMap<String, HashSet<String>>();
        for(String key : map.keySet()){
            map.get(key).remove("Apple");
        }
        for(HashSet<String> val : map.values()){
            val.remove("Apple");
        }

        
    }
}
