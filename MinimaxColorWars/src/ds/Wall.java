package ds;

import java.awt.Rectangle;
import java.util.ArrayList;

/** Walls present within the Board. */
public class Wall implements Selectable {
    boolean enabled;
    GraphicsObject<Rectangle> object;

    public Wall(boolean enabled, GraphicsObject<Rectangle> object) {
        this.enabled = enabled;
        this.object = object;
    }

    /** Wide implementation. */
    public static Wall wide() {
        return new Wall(false, new GraphicsObject<Rectangle>(color, new Rectangle(0, 0, 90, 18)));
    }

    /** High implementation. */
    public static Wall high() {
        return new Wall(false, new GraphicsObject<Rectangle>(new Rectangle(0, 0, 18, 90)));
    }

    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        return mouse_x - object.shape.x < object.shape.width && mouse_y - object.shape.y < object.shape.height;
    }


}

