import java.awt.Color;
import java.awt.Graphics2D;

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


    // ///We slowly lerp the pointer.
    // public void move(int x_lerp){
    //     var original_x = x;
    //     for(var i = 0; i < 500; i++){
    //         SortDriver.sleep_safe(1);
    //         x += (x_lerp - original_x)/500.0;
    //     }
    // }



    /**A helper function that acts as a weighted midpoint.*/
    public static int perce(double x, double per, double size){
        return (int)(x + per * size);
    }
}
