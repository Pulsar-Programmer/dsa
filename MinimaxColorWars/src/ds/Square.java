package ds;

import java.awt.Rectangle;

/**Squares within the board. */
public class Square implements Selectable {
    GraphicsObject<Rectangle> object;

    public Square(GraphicsObject<Rectangle> object) {
        this.object = object;
    }

    




    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        return mouse_x - object.shape.x < object.shape.width && mouse_y - object.shape.y < object.shape.height;
    }

    
}
