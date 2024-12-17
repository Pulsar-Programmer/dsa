import java.awt.*;
import java.util.ArrayList;

/**A class for holding boxes in a particular arrangement. */
public class Holder {

    ///We have elements, the starting point, and the content of the holder.
    ArrayList<Box> elements;
    Point zero;
    char content;

    public Holder() {
        elements = new ArrayList<>();
        zero = new Point(0, 0);
        content = '0';
    }

    public Holder(Point zero) {
        this();
        this.zero = zero;
    }

    public Holder(ArrayList<Box> elements, Point zero) {
        this(zero);
        this.elements = elements;
    }

    public Holder(ArrayList<Box> elements, Point zero, char content) {
        this(elements, zero);
        this.content = content;
    }

    public Holder(Point zero, char content) {
        this();
        this.zero = zero;
        this.content = content;
    }

    /**Adds a box to the holder. */
    public void addBox(Box box) {
        elements.add(box);
    }

    /**Draws the holder with centering corrections. */
    public void draw(Graphics2D ctx){
        ctx.setFont(Slicer.FONT);
        var centering = !elements.isEmpty() ? elements.get(0).width / 2 : 0;


        // Get the width of the text (number) to center it properly
        FontMetrics fm = ctx.getFontMetrics();
        int textWidth = fm.charWidth(content);

        ctx.drawString(Character.toString(content), zero.x + centering - textWidth / 2, zero.y+50);
    }

    /**Spaces out the boxes and places 10px in between them. */
    public void spaceOut() {
        if(elements.size() >= 1){
            var box = elements.get(0);
            box.lerp(zero);
        }
        for (int i = 1; i < elements.size(); i++) {
            Box prevBox = elements.get(i - 1);
            Box currentBox = elements.get(i);

            int newY = (int)prevBox.y - prevBox.height - 10;
            currentBox.lerp(new Point((int)prevBox.x, newY));
        }
    }
}