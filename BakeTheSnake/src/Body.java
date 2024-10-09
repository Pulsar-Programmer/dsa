import java.awt.Point;
import java.util.Optional;

public class Body {
    
    private Point location;
    private Body next;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Body getNext() {
        return next;
    }

    public void setNext(Body next) {
        this.next = next;
    }

    public Body(){
        location = new Point(0, 0);
        next = null;
    }

    public Body(int a, int b){
        location = new Point(a, b);
        next = null;
    }

    public Body(Point location, Body next) {
        this.location = location;
        this.next = next;
    }

    public Body(int row, int col, Body p){
        this.location = new Point(row, col);
        this.next = p;
    }
    
}
