import java.awt.Point;

public class Snake {
    private Direction direction;
    private Point head;
    private Body next;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public Body getNext() {
        return next;
    }

    public void setNext(Body next) {
        this.next = next;
    }

    public Snake(){
        direction = Direction.Down;
        head = new Point(4, 4);
        next = new Body(new Point(4, 3), new Body(4, 2));
    }

    public Snake(Direction direction, Point head, Body next) {
        this.direction = direction;
        this.head = head;
        this.next = next;
    }

    ///Returns if the snake is alive or not after the command.
    boolean update(char command){
        switch(command) {
            case 'M' -> {
                
            }
            case 'U' -> {
                
            }
            case 'D' -> {
                
            }
            case 'L' -> {
                
            }
            case 'R' -> {
                
            }
            case 'F' -> {

            }
            case 'E' -> {

            }
        }
        //TODO
        return false;
    }


}

enum Direction {
    Up,
    Down,
    Left,
    Right;
}