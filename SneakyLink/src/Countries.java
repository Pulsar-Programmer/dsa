import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class Countries {
    private LinkedList<Entry>[] entries;
    
    private Countries(LinkedList<Entry>[] entries){
        this.entries = entries;
    }

    public Countries(){
        entries = with_capacity(2).entries;
    }

    /** This creates a new HashMap with a set capacity. */
    public static Countries with_capacity(int cap) {
        ///We first create a new set of entries and fill them with new Lists.
        LinkedList<Entry>[] entries = new LinkedList[cap];
        Arrays.fill(entries, new LinkedList<Entry>());
        return new Countries(entries);
    }

    /** This creates a new HashMap from a CSV File */
    public static Countries loadFromFile(File file) throws FileNotFoundException {
        ///We create our Countries HashMap.
        Countries countries = Countries.with_capacity(250);

        ///We create a scanner from the file.
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); ///Once to remove name.
        scanner.nextLine(); ///Twice to remove headers.

        ///We scan through first each line.
        while (scanner.hasNext()) {
            ///Next we create another subscanner through that line, splitting on commas.
            Scanner second = new Scanner(scanner.nextLine());
            second.useDelimiter(",");

            ///Now, we deposit all of the entries into our map.
            Key key = new Key(second.next());
            Value value = new Value(second.next(), second.next(), second.nextInt(), second.nextInt(), second.next(), second.next(), second.next(), second.next(), second.next(), second.next(), second.next(), second.nextInt(), second.next(), second.nextInt(), second.nextFloat(), second.nextFloat(), second.next(), second.next());
            Entry entry = new Entry(key, value);

            countries.put(entry);

            second.close();
        }
        scanner.close();

        return countries;
    }

    /** This returns the capacity of the HashMap. */
    public int cap(){
        return entries.length;
    }

    /** This finds the amount of filled slots. */
    public int len(){
        var counter = 0;
        for (LinkedList<Entry> collist : entries) {
            counter += collist.isEmpty() ? 0 : 1;
        }
        return counter;
    }

    /** Returns the Load Factor which is simply just the length divided by the capacity. */
    public double loadFactor(){
        return (double)len() / (double)cap();
    }

    /** This inserts a value into the HashMap given a Key-Value pair. */
    public void put(Entry entry){
        unchecked_put(entry);
        try_rehash();
    }

    /** This inserts a value disregarding load factor. */
    private void unchecked_put(Entry entry){
        final var index = entry.key.hashCode() % cap();
        entries[index].add(entry);
    }

    /** This inserts a value into the HashMap given both a KV Pair and the intended index. */
    public void put(int idx, Entry entry){
        entries[idx].add(entry);
        try_rehash();
    }

    /** This gets a value from the HashMap given a Key. */
    public Optional<Value> get(Key key){
        ///We get the index, find the entry, then look in the list for a matching key.
        final var index = key.hashCode() % cap();
        for (Entry entry : entries[index]) {
            if(key.equals(entry.key)){
                return Optional.of(entry.value);
            }
        }
        ///If a matching key exists, we return it, but if it does not, we return an empty Optional type (I REALLY don't like `null`.).
        return Optional.empty();
    }

    /** This removes a KV pair from the HashMap given it's key and returns it, or returns nothing if nothing is present. */
    public Optional<Value> remove(Key key){
        ///We track the index, find the entry, then look in the list for a matching key.
        final var index = key.hashCode() % cap();
        Integer removed_idx = null;
        final var list = entries[index];
        ///We loop through the list and find the index mathcing the key.
        for (var i = 0; i < list.size(); i++) {
            final var entry = list.get(i);
            if(key.equals(entry.key)){
                removed_idx = i;
            }
        }
        ///We return a removed value only if there exists a removed index.
        return removed_idx == null ? Optional.empty() : Optional.of(list.remove(removed_idx.intValue()).value);
    }

    /** This attempts to rehash if the load_factor requirement is met. */
    public void try_rehash(){
        ///We only rehash if the load_factor is greater than or equal to 0.75, but since there is only PartialEq, we use `>`.
        if(loadFactor() > 0.75){
            rehash();
        }
    }

    /** This creates a new HashMap with double the capacity to fit more objects. */
    public void rehash(){
        ///We start with our old entries, then creating new ones.
        var old_entries = entries;
        entries = with_capacity(cap()*2).entries;
        ///Next, we look through each placement and if there are items, we place them to the next HashMap, without checking the `len` which is an expensive operation.
        for (LinkedList<Entry> placement : old_entries) {
            for (Entry entry : placement) {
                unchecked_put(entry);
            }
        }
    }

    @Override
    public String toString() {

        // var random_10 = ;
        

        return MessageFormat.format(
            "Countries [ load_factor={0}, size={1}, capacity={2}, first_ten={3} ]", 
            loadFactor(),
            len(),
            cap()
            // first_ten
        );
    }
}
