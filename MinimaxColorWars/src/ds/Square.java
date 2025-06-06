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

    /** Row of the Square. */
    public int row(){
        return map() / 9;
    }
    /** Column of the Square. */
    public int col(){
        return map() % 9;
    }

    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        return mouse_x < object.shape.width + object.shape.x 
        && mouse_x > object.shape.x
        && mouse_y < object.shape.height + object.shape.y
        && mouse_y > object.shape.y;
    }

    @Override
    public int map() {
        var x_type = (object.shape.x - 5 - 18) / (90 + 18);
        var y_type = (object.shape.y - 5 - 18) / (90 + 18);
        return x_type + y_type * 9;
    }

    
}
