package ds;

import java.awt.Rectangle;

/**Squares within the board. */
public class Square implements Selectable {
    GraphicsObject<Rectangle> object;



    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        // return mouse_x - object.position.x
        return false;
    }

    
}
