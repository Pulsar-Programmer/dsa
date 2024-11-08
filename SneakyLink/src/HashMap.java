import java.util.Arrays;
import java.util.LinkedList;

public class HashMap {
    private LinkedList<Entry>[] entries;
    
    private HashMap(LinkedList<Entry>[] entries){
        this.entries = entries;
    }

    public static HashMap with_capacity(int cap) {
        LinkedList<Entry>[] entries = new LinkedList[cap];
        return new HashMap(entries);
    }
}
