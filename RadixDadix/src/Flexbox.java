import java.awt.*;
import java.util.ArrayList;

public class Flexbox {

    ArrayList<Box> elements;
    Point zero;
    char content;

    // Constructor to initialize the ArrayList
    public Flexbox() {
        elements = new ArrayList<>();
        zero = new Point(0, 0);
        content = '0';
    }

    public Flexbox(Point zero) {
        this();
        this.zero = zero;
    }

    public Flexbox(ArrayList<Box> elements, Point zero) {
        this(zero);
        this.elements = elements;
    }

    public Flexbox(ArrayList<Box> elements, Point zero, char content) {
        this(elements, zero);
        this.content = content;
    }

    public Flexbox(Point zero, char content) {
        this();
        this.zero = zero;
        this.content = content;
    }

    // Method to add a new box with specified width
    public void addBox(Box box) {
        elements.add(box);
    }

    public void draw(Graphics2D ctx){
        ctx.setFont(Slicer.FONT);
        var centering = !elements.isEmpty() ? elements.get(0).height / 2 : 0;

        FontMetrics fm = ctx.getFontMetrics();
        int textHeight = fm.getAscent();


        ctx.drawString(Character.toString(content), zero.x-10, zero.y + centering + textHeight / 2);
    }

    // Method to space out the boxes, ensuring each box is placed after the previous one with at least 20 pixels between them
    public void spaceOut() {
        if(elements.size() >= 1){
            var box = elements.get(0);
            box.lerp(zero);
        }
        for (int i = 1; i < elements.size(); i++) {
            Box prevBox = elements.get(i - 1);
            Box currentBox = elements.get(i);
            // Set the x of the current box based on the previous box's width + 20 space
            int newX = (int)prevBox.x + prevBox.width + 10;
            currentBox.lerp(new Point(newX, (int)prevBox.y));
        }
    }
}