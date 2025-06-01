package ds;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import proc.main.App;

/** Walls present within the Board. */
public class Wall implements Selectable {
    boolean enabled;
    public GraphicsObject<Rectangle> object;

    public Wall(boolean enabled, GraphicsObject<Rectangle> object) {
        this.enabled = enabled;
        this.object = object;
    }

    /** Wide implementation. */
    public static Wall wide(Point pos) {
        return new Wall(false, new GraphicsObject<Rectangle>(Color.black, new Rectangle(pos.x, pos.y, 90, 18)));
    }

    /** High implementation. */
    public static Wall high(Point pos) {
        return new Wall(false, new GraphicsObject<Rectangle>(Color.black, new Rectangle(pos.x, pos.y, 18, 90)));
    }

    /** Enables the wall. */
    public void enable(){
        enabled = true;
        object.color = App.mantle;
    }

    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        return mouse_x - object.shape.x < object.shape.width && mouse_y - object.shape.y < object.shape.height;
    }


}

