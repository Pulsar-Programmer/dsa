import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Pointer {
    private double x;
    private double y;
    private double size;
    private Color color;

    public Pointer(Color color, double x, double y, double size){
        this.y = y;
        this.color = color;
        this.x = x;
        this.size = size;
    }
    
    /** We draw the pointer. */
    public void draw(Graphics2D ctx){
        ctx.setColor(color);
        ctx.fillPolygon(new int[]{perce(x, 0.5, size), perce(x, 1, size), perce(x, 1.5, size)}, new int[]{perce(y, 1., size), (int)y, perce(y, 1., size)}, 3);
        ctx.setColor(Color.black);
        ctx.drawPolygon(new int[]{perce(x, 0.5, size), perce(x, 1, size), perce(x, 1.5, size)}, new int[]{perce(y, 1., size), (int)y, perce(y, 1., size)}, 3);
    }


    /** Also known as the `common lerp`, it is the lerp adjusted for the animation speed. */
    public void lerp(Point target){
        lerp(target, RadixGraphics.LERP_TIME);
    }

    ///We lerp the box with a time.
    public void lerp(Point target, double time){
        double dx = target.x - x;
        double dy = target.y - y;
        for(var i = 0; i < time; i++){
            App.sleep_safe(1);
            x += dx/time;
            y += dy/time;
        }
    }



    /**A helper function that acts as a weighted midpoint.*/
    public static int perce(double x, double per, double size){
        return (int)(x + per * size);
    }
}
