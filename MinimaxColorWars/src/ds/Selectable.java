package ds;


/** Trait that allows the entering within a TriggerPool. */
public interface Selectable {
    public boolean cmp(int mouse_x, int mouse_y); // this will go away once we have map
    public int map();
}