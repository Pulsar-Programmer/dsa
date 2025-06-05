package ds;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import proc.main.App;

/** Walls present within the Board. */
public class Wall implements Selectable {
    public boolean enabled;
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

    /** Tests whether the wall is vertical or not. */
    public boolean is_vertical(){
        return object.shape.width < object.shape.height;
    } 

    /** Enables the wall. */
    public void enable(){
        enabled = true;
        object.color = App.mantle;
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
        var b_type = is_vertical();

        var x_type = (object.shape.x - 5 - 18) / (90 + 18);
        var y_type = (object.shape.y - 5) / (90 + 18);
        if(b_type){
            x_type = (object.shape.x - 5) / (90 + 18);
            y_type = (object.shape.y - 5 - 18) / (90 + 18);
        }

        return x_type + y_type * 10 + (b_type ? 99 : 0);
    }

}

