import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Slicer {
    public final static Font FONT = new Font("Fira Code", Font.PLAIN, 10);

    public int x, y, size;
    public char content;
    
    
    public Slicer(int x, int y, int size, char content) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.content = content;
    }


    public void draw(Graphics2D ctx){
        ctx.setFont(FONT);
        
        ctx.setColor(Color.BLACK);
        ctx.drawLine(x, y - size, x, y + size);

        // Get the width of the text (number) to center it properly
        FontMetrics fm = ctx.getFontMetrics();
        int textWidth = fm.charWidth(content);

        // Calculate the X-coordinate to center the text horizontally next to the line
        int textX = x - textWidth / 2;
        int textY = y + size + fm.getAscent() + 5; // Offset slightly below the line

        // Draw the number
        ctx.drawString(Character.toString(content), textX, textY);
    }

    public void move_between(Box one, Box two){
        size = one.height / 8 * 9;

        int separatorX1 = one.x + one.width; // Right edge of Box 1
        int separatorX2 = two.x; // Left edge of Box 2

        int middle_of_edges = (separatorX1 + separatorX2) / 2;
        int y_middle = one.y + one.height / 2;

        x = middle_of_edges;
        y = y_middle;
    }

    
}
