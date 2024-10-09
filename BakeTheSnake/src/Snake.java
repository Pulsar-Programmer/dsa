import java.awt.Point;
import java.util.Arrays;

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
        board = new char[10][10];
        for(var i = 0; i < 10; i++){
            for(var j = 0; j < 10; j++){
                board[i][j] = i == 0 || j == 0 || i==9 || j==9 ? App.WALL : App.EMPTY;
            }
        }
    }

    public Snake(int direction, Point head, Body next, char[][] board) {
        this.direction = direction;
        this.head = head;
        this.next = next;
        this.board = board;
    }

    public Snake(int dir, int row, int col, Body p){
        this.direction = dir;
        this.head = new Point(row, col);
        this.next = p;
        board = new char[10][10];
        for(var i = 0; i < 10; i++){
            for(var j = 0; j < 10; j++){
                board[i][j] = i == 0 || j == 0 || i==9 || j==9 ? App.WALL : App.EMPTY;
            }
        }
    }

    @Override
    public String toString() {
        String pusher = "";
        for(char[] i: this.board){
            for(char j: i){
                pusher += j;
                pusher += ' ';
            }
            pusher += '\n';
        }
        return pusher;
    }

    /**Returns if the snake is alive or not after the command. */
    boolean update(String commands){
        var commandsGood = commands.toCharArray();
        for (var c : commandsGood){
            if(!update(c)){
                return false;
            }
        }
        return true;
    }

    // /**Returns if the snake is alive or not after the command. */
    boolean update(char command){
        switch(command) {
            ///We set the direction to each case.
            ///The extra statement before setting is used to guarantee that they cannot make a 180 degree turn.
            //#region Directions
            case 'R' : {
                if ((direction + 2) % 4 == 0){
                    return true;
                }
                direction = 0;          
            } break;
            case 'U' : {
                if ((direction + 2) % 4 == 1){
                    return true;
                }
                direction = 1;
            } break;
            case 'L' : {
                if ((direction + 2) % 4 == 2){
                    return true;
                }
                direction = 2;
            } break;
            case 'D' : {
                if ((direction + 2) % 4 == 3){
                    return true;
                }
                direction = 3;
            } break;
            //#endregion


            case 'M' : {
                ///We find the next position that there is to be explored.
                final Point next_pos = advanced(head, direction);
                ///If that next point equals the wall, then we detect a wall.
                final boolean found_wall = 
                    board[next_pos.y][next_pos.x] == App.WALL;
                ///We detect a snake only if there is a snake in two other places around it. If there is only one snake then that must be the tail.
                final boolean found_snake = 
                    board[next_pos.y][next_pos.x] == App.SNAKE && (
                        (board[next_pos.y + 1][next_pos.x] == App.SNAKE ? 1 : 0) +
                        (board[next_pos.y + 1][next_pos.x] == App.SNAKE ? 1 : 0) +
                        (board[next_pos.y + 1][next_pos.x] == App.SNAKE ? 1 : 0) +
                        (board[next_pos.y + 1][next_pos.x] == App.SNAKE ? 1 : 0)
                    >= 2);
                ///If we find any of these, the snake perishes.
                if(found_snake || found_wall){
                    return false;
                }
                
                //TODO



            } break;
            case 'F' : {

            } break;
            case 'E' : {

            } break;
        }
        //TODO
        return true;
    }

    private char direction_char() {
        return switch(direction){
            case 0 -> {
                yield App.RIGHT;
            }
            case 1 -> {
                yield App.UP;
            }
            case 2 -> {
                yield App.LEFT;
            }
            case 3 -> {
                yield App.DOWN;
            }
            default -> {
                yield App.DOWN;
            }
        };
    }

    private static Point advanced(Point current, int direction){
        return switch(direction){
            case 0 -> {
                yield new Point(current.x - 1, current.y);
            }
            case 1 -> {
                yield new Point(current.x, current.y + 1);
            }
            case 2 -> {
                yield new Point(current.x + 1, current.y);
            }
            case 3 -> {
                yield new Point(current.x, current.y - 1);
            }
            default -> {
                yield new Point(current.x - 1, current.y);
            }
        };
    }

    

}