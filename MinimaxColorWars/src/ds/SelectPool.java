package ds;

import java.util.ArrayList;

/** A collection of objects, that, when an action is called, the trigger pool triggers the proper object. */
public class SelectPool<T extends Selectable> {
    //represents a pool of objects
    //a specific method is called on the object when the mouse meets this condition

    /**Contents of the pool. */
    ArrayList<T> pool;

    public SelectPool(ArrayList<T> pool) {
        this.pool = pool;
    }
    
    /** Default implementation. */
    public static <T extends Selectable> SelectPool<T> Default() {
        return new SelectPool<T>(new ArrayList<T>());
    }

    /** Selects items from the Trigger pool. */
    public ArrayList<T> select(int mouse_x, int mouse_y){
        ArrayList<T> list = new ArrayList<>();
        for (T selectable : pool) {
            if(selectable.cmp(mouse_x, mouse_y)){
                list.add(selectable);
            }
        }
        return list;
    }
}

/** Trait that allows the entering within a TriggerPool. */
interface Selectable {
    public boolean cmp(int mouse_x, int mouse_y);
}