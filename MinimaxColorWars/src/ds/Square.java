package ds;

import java.awt.Point;
import java.awt.Rectangle;

import proc.main.App;

/**Squares within the board. */
public class Square implements Selectable {
    public GraphicsObject<Rectangle> object;

    public Square(GraphicsObject<Rectangle> object) {
        this.object = object;
    }

    /** Sidecar implementation. */
    public static Square sidecar(Point pos) {
        return new Square(new GraphicsObject<Rectangle>(App.sidecar, new Rectangle(pos.x, pos.y, 90, 90)));
    }

    /** Muddy Waters implementation. */
    public static Square muddy_waters(Point pos) {
        return new Square(new GraphicsObject<Rectangle>(App.muddy_waters, new Rectangle(pos.x, pos.y, 90, 90)));
    }

    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        return mouse_x - object.shape.x < object.shape.width && mouse_y - object.shape.y < object.shape.height;
    }

    
}
