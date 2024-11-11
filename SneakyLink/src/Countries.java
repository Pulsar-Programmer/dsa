import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class Countries {
    private LinkedList<Entry>[] entries;
    
    private Countries(LinkedList<Entry>[] entries){
        this.entries = entries;
    }

    /** This creates a new HashMap with a set capacity. */
    public static Countries with_capacity(int cap) {
        LinkedList<Entry>[] entries = new LinkedList[cap];
        Arrays.fill(entries, new LinkedList<Entry>());
        return new Countries(entries);
    }

    /** This returns the capacity of the HashMap. */
    public int cap(){
        return entries.length;
    }

    public void put(Entry entry){
        final var index = entry.key.hashCode() % cap();
        entries[index].add(entry);
    }

    public Optional<Value> get(Key key){
        final var index = key.hashCode() % cap();
        for (Entry entry : entries[index]) {
            if(key.equals(entry.key)){
                return Optional.of(entry.value);
            }
        }
        return Optional.empty();
    }
}
