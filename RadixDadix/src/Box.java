import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Box {
    public double x, y;
    public int width, height;
    public String content;
    // private int selected;

    public Box(double x, double y, int width, int length, String content) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        this.content = content;
        // selected = -1;
    }

    // public int getSelected() {
    //     return selected;
    // }
    // public void setSelected(int selected) {
    //     this.selected = selected;
    // }



    public void draw(Graphics2D ctx){
        ctx.setFont(RadixGraphics.FONT);
        ctx.setColor(Color.black);

        FontMetrics fm = ctx.getFontMetrics();
        int yOffset = fm.getAscent();

        ctx.drawString(content, (int)x + 5, (int)y + yOffset);
        ctx.drawRect((int)x, (int)y, width, height);
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

    // public void move(Point target){
    //     move(target, RadixGraphics.SPEED);
    // }

    ///We move the box with a speed.
    // public void move(Point target, double speed){
    //     int dx = target.x - x; // Difference in x direction
    //     int dy = target.y - y; // Difference in y direction

    //     // Normalize the direction for movement along x and y
    //     double distance = Math.sqrt(dx * dx + dy * dy); // Hypotenuse, total distance to the target
    //     double moveX = (dx / distance) * speed; // Step size in the x direction
    //     double moveY = (dy / distance) * speed; // Step size in the y direction

    //     while(true){
    //         App.sleep_safe(1);
    //         x += moveX;
    //         y += moveY;

    //         // If the box has reached the target in both x and y directions, stop
    //         if ((dx > 0 && x >= target.x) || (dx < 0 && x <= target.x)) {
    //             x = target.x; // Ensure no overshoot in the x direction
    //         }

    //         if ((dy > 0 && y >= target.y) || (dy < 0 && y <= target.y)) {
    //             y = target.y; // Ensure no overshoot in the y direction
    //         }

    //         // If the box has reached both target.x and target.y, stop the movement
    //         if (x == target.x && y == target.y) {
    //             break;
    //         }
    //     }
    // }




    //TODO
    public static void draw_selected_string(Graphics2D ctx, int idx, String text, int x, int y){
        FontMetrics fm = ctx.getFontMetrics();

        // Calculate the width of the string up to the highlighted character
        int charStartX = x;
        for (int i = 0; i < idx; i++) {
            charStartX += fm.charWidth(text.charAt(i));
        }

        // Draw the part of the string before the highlighted character
        ctx.setColor(Color.BLACK);
        ctx.drawString(text.substring(0, idx), x, y);

        // Draw the highlighted character
        char highlightedChar = text.charAt(idx);
        ctx.setColor(Color.RED);
        ctx.drawString(String.valueOf(highlightedChar), charStartX, y);

        // Optionally underline the highlighted character
        int charWidth = fm.charWidth(highlightedChar);
        int underlineY = y + 2; // Slightly below the baseline
        ctx.drawLine(charStartX, underlineY, charStartX + charWidth, underlineY);

        // Draw the rest of the string after the highlighted character
        ctx.setColor(Color.BLACK);
        ctx.drawString(text.substring(idx + 1), charStartX + charWidth, y);
    }

    // @Override
    // public Point toFlexPoint() {
    //     return new Point(x, width);
    // }
}
