import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

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

    public Slicer(Box one, Box two, char content) {
        move_between(one, two);
        this.content = content;
    }

    ///We draw the line and the little text under it for the separator.
    public void draw(Graphics2D ctx){
        ctx.setFont(FONT);
        
        ctx.setColor(Color.BLACK);
        ctx.drawLine(x, y - size, x, y + size);

        ///We get the width of the text.
        FontMetrics fm = ctx.getFontMetrics();
        int textWidth = fm.charWidth(content);

        int textX = x - textWidth / 2;
        int textY = y + size + fm.getAscent() + 5;

        ctx.drawString(Character.toString(content), textX, textY);
    }

    public void move_between(Box one, Box two){
        size = one.height / 8 * 9;

        int separatorX1 = (int)one.x + one.width; 
        int separatorX2 = (int)two.x;

        int middle_of_edges = (separatorX1 + separatorX2) / 2;
        int y_middle = (int)one.y + one.height / 2;

        x = middle_of_edges;
        y = y_middle;
    }
}
