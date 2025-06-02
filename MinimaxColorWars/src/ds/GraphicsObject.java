package ds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

/** An object on the screen. */
public class GraphicsObject<T extends Shape> {
    // Point position;
    Color color;
    public T shape;

    public GraphicsObject(Color color, T shape) {
        // this.position = position;
        this.color = color;
        this.shape = shape;
    }

    /** Draws the shape. */
    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fill(shape);
    }


}
