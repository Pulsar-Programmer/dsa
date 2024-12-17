import java.awt.Color;
import java.awt.Graphics2D;

public class Box {
    private int x, y, width, height;
    private String content;


    

    public Box(int x, int y, int width, int length, String content) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        this.content = content;
    }




    public void draw(Graphics2D ctx, int yOffset){
        ctx.setColor(Color.black);
        ctx.drawString(content, x, y);
        ctx.drawRect(x - 5, y - yOffset + 5, width, height);
    }


}
