import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Box {
    public int x, y, width, height;
    private String content;
    // private int selected;

    public Box(int x, int y, int width, int length, String content) {
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

        ctx.drawString(content, x + 5, y + yOffset);
        ctx.drawRect(x, y, width, height);
    }







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


}
