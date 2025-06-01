package ds;

import java.util.ArrayList;

/** A collection of objects, that, when an action is called, the trigger pool triggers the proper object. */
public class SelectPool<T> {
    //represents a pool of objects
    //a specific method is called on the object when the mouse meets this condition

    ArrayList<Selectable> pool;

    // public Selectable select(int mouse_x, int mouse_y){
        
    // }
}

/** Trait that allows the entering within a TriggerPool. */
interface Selectable {
    public boolean cmp(int mouse_x, int mouse_y);
}