package ds;

import java.awt.Rectangle;

/** Walls present within the Board. */
public class Wall implements Selectable {
    boolean enabled;
    GraphicsObject<Rectangle> object;

    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cmp'");
    }


}

