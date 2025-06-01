package ds;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public class GraphicsObject<T extends Shape> {
    Point position;
    T shape;

    public GraphicsObject(Point position, T shape) {
        this.position = position;
        this.shape = shape;
    }

    public void draw(Graphics2D g2d){
        g2d.draw(shape);
    }


}
