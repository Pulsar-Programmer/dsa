package ds;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

/** A collection of objects, that, when an action is called, the trigger pool triggers the proper object. */
public class SelectPool<T extends Selectable> {
    //represents a pool of objects
    //a specific method is called on the object when the mouse meets this condition

    /**Encapsulated contents of the pool. */
    private ArrayList<T> pool;

    private SelectPool(ArrayList<T> pool) {
        this.pool = pool;
    }

    /** Creates a locker-type vec. */
    // public static <T extends Selectable> SelectPool<T> with_cap(int cap){
    //     var list = new ArrayList<T>();
    //     for(var i = 0; i < cap; i++){
    //         list.add(null);
    //     }
    //     return new SelectPool<T>(list);
    // }

    /** Attempts to obtain the object at the index. */
    public Optional<T> try_get(int idx){
        try {
            var obj = pool.get(idx);
            return obj == null ? Optional.empty() : Optional.of(obj);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /** Pushes the object and indexes it within the list. */
    public void push(T item){
        var idx = item.map();
        while(idx >= pool.size()){
            pool.add(null);
        }
        pool.set(idx, item);
    }

    /** Iterates over each item in the pool (excluding null ones). */
    public void forEach(Consumer<T> action) {
        var iterator = pool.iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if(next == null) continue;
            action.accept(next);
        }
    }
    
    /** Default implementation. */
    public static <T extends Selectable> SelectPool<T> Default() {
        return new SelectPool<T>(new ArrayList<T>());
    }

    /** Selects items from the Trigger pool. */
    public ArrayList<T> select(int mouse_x, int mouse_y){
        ArrayList<T> list = new ArrayList<>();
        forEach(selectable -> {
            if(selectable.cmp(mouse_x, mouse_y)){
                list.add(selectable);
            }
        });
        return list;
    }
}