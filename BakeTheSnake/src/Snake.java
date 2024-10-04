import java.awt.Point;

public class Snake {




    private int direction;
    private Point head;
    private Body next;
    private char[][] board;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
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
        direction = 3;
        head = new Point(4, 4);
        next = new Body(new Point(4, 3), new Body(4, 2));
        //TODO fill board
    }

    public Snake(int direction, Point head, Body next) {
        this.direction = direction;
        this.head = head;
        this.next = next;
        //TODO fill board
    }

    public Snake(int dir, int row, int col, Body p){
        this.direction = dir;
        this.head = new Point(row, col);
        this.next = p;
    }

    ///Returns if the snake is alive or not after the command.
    boolean update(char command){
        switch(command) {
            case 'U' : {
                direction = 1;
            } break;
            case 'D' : {
                direction = 3;
            } break;
            case 'L' : {
                direction = 2;
            } break;
            case 'R' : {
                direction = 0;          
            } break;











            case 'M' : {
                final Point next_pos = advanced(head, direction);
                final boolean found_wall = 
                    next_pos.x <= -1 || 
                    next_pos.x >= 10||
                    next_pos.y <= -1||
                    next_pos.y >= 10 ;
                final boolean found_snake = 
                    next_pos

            } break;
            case 'F' : {

            } break;
            case 'E' : {

            } break;
        }
        //TODO
        return false;
    }

    // private char direction_char() {
    //     return switch(direction){
    //         case 0 -> {
    //             yield App.RIGHT;
    //         },
    //         case 1 -> {
    //             yield App.UP;
    //         },
    //         case 2 -> {
    //             yield App.LEFT;
    //         },
    //         case 3 -> {
    //             yield App.DOWN;
    //         },
    //     };
    // }

    private static Point advanced(Point current, int direction){
        //     return switch(direction){
        //     case 0 -> {
        //         yield App.RIGHT;
        //     },
        //     case 1 -> {
        //         yield App.UP;
        //     },
        //     case 2 -> {
        //         yield App.LEFT;
        //     },
        //     case 3 -> {
        //         yield App.DOWN;
        //     },
        // };
        //TODO
        return null;
    }


}