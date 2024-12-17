import java.awt.*;
import java.util.ArrayList;

public class Flexbox {

    private ArrayList<Box> elements;
    Point zero;

    // Constructor to initialize the ArrayList
    public Flexbox() {
        elements = new ArrayList<>();
        zero = new Point(0, 0);
    }

    public Flexbox(Point zero) {
        this();
        this.zero = zero;
    }

    public Flexbox(ArrayList<Box> elements, Point zero) {
        this.elements = elements;
        this.zero = zero;
    }

    // Method to add a new box with specified width
    public void addBox(Box box) {
        elements.add(box);
        spaceOut();
    }

    public void draw(Graphics2D ctx){
        // ctx.setFont(Slicer.FONT);
    }

    // Method to space out the boxes, ensuring each box is placed after the previous one with at least 20 pixels between them
    public void spaceOut() {
        if(elements.size() >= 1){
            var box = elements.get(0);
            box.lerp(zero, 1000);
            box.x = zero.x;
            box.y = zero.y;
        }
        for (int i = 1; i < elements.size(); i++) {
            Box prevBox = elements.get(i - 1);
            Box currentBox = elements.get(i);
            // Set the x of the current box based on the previous box's width + 20 space
            int newX = (int)prevBox.x + prevBox.width + 20;
            currentBox.x = newX;
            currentBox.y = prevBox.y;
        }
    }
}