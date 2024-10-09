import java.awt.Point;
import java.util.Optional;
import java.util.Stack;

public class Snake {

    private int direction;
    private Point head;
    private Body next;
    private char[][] board;
    boolean is_alive;
    boolean eating_stack;

    public boolean isEating_stack() {
        return eating_stack;
    }

    public void setEating_stack(boolean eating_stack) {
        this.eating_stack = eating_stack;
    }

    public boolean isIs_alive() {
        return is_alive;
    }

    public boolean isAlive(){
        return isIs_alive();
    }

    public void setIs_alive(boolean is_alive) {
        this.is_alive = is_alive;
    }

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
        clear_board();
        is_alive = true;
    }

    public Snake(int direction, Point head, Body next, char[][] board, boolean is_alive, boolean eating_stack) {
        this.direction = direction;
        this.head = head;
        this.next = next;
        this.board = board;
        this.is_alive = is_alive;
        this.eating_stack = eating_stack;
    }

    public Snake(int dir, int row, int col, Body p){
        this.direction = dir;
        this.head = new Point(row, col);
        this.next = p;
        clear_board();
        is_alive = true;
    }

    /** Returns a depiction of the board containing the Snake. */
    @Override
    public String toString() {
        ///We have a pusher String.
        String pusher = "";
        for(char[] i: this.board){
            for(char j: i){
                ///We push the string. If it is a Snake and the Snake is dead, we print Xs instead.
                pusher += j == Driver.SNAKE && !is_alive ? Driver.DEATH : j;
                ///We push whitespace for readability.
                pusher += ' ';
            }
            ///We push the new line corresponding to the next row.
            pusher += '\n';
        }
        return pusher;
    }

    /** Returns if the snake is alive or not after the command and executes the series of commands given by String. */
    boolean update(String commands){
        var commandsGood = commands.toCharArray();
        for (var c : commandsGood){
            System.out.println("Starting next command!");
            if(!update(c)){
                return false;
            }
            System.out.println(toString()); //TODO
        }
        return true;
    }

    // /**Returns if the snake is alive or not after the command and executes the command preesented. */
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
                System.out.println("Doing D");
                if ((direction + 2) % 4 == 3){
                    return true;
                }
                direction = 3;
                System.out.println("Doing D2");
            } break;
            //#endregion

            case 'M' : {

                //#region Collision

                ///We find the next position that there is to be explored.
                final Point next_pos = advanced(head, direction);
                ///If that next point is out of the bounds, then we detect a wall.
                final boolean found_wall = 
                    !next_pos.equals(clamped(next_pos));
                    // board[next_pos.y][next_pos.x] == Driver.WALL;
                ///We detect a snake only if there is a snake in two other places around it. If there is only one snake then that must be the tail.
                final boolean found_snake = 
                    board[next_pos.y][next_pos.x] == Driver.SNAKE && (
                        (char_cmp(Driver.SNAKE, try_get(advanced(next_pos, 0))) ? 1 : 0) +
                        (char_cmp(Driver.SNAKE, try_get(advanced(next_pos, 1))) ? 1 : 0) +
                        (char_cmp(Driver.SNAKE, try_get(advanced(next_pos, 2))) ? 1 : 0) +
                        (char_cmp(Driver.SNAKE, try_get(advanced(next_pos, 3))) ? 1 : 0)
                    >= 2);
                ///If we find any of these, the snake perishes.
                if(found_snake || found_wall){
                    System.out.println("Snake" + found_snake);
                    System.out.println("WAll" + found_wall);
                    is_alive = false;
                    return false;
                }

                //#endregion

                //#region Eating Stack

                ///Before we can start moving, we must introduce the next from the eating stack.

                ///If we ate something, we must grow while moving.
                if(eating_stack){
                    ///We find the point to add from the previous fruit.
                    final Point to_add = new Point(head.x, head.y);

                    ///We move up head.
                    head = next_pos;

                    ///We snip off the first body and clone the next element.
                    final Body snipped = to_owned(next);

                    ///We add a body in the place the head used to be.
                    next = new Body(to_add, snipped);

                    ///We finish the move operation.
                    eating_stack = false;
                    return true;
                }

                //#endregion

                //#region Moving
                
                ///We start by snipping and storing the first element.
                Body snipped = next;
                ///We store a runner to go to last place.
                Body runner = next;
                ///As long as the next link is present...
                while(runner.getNext() != null){
                    ///If we are currently on the second to last...
                    if(runner.getNext().getNext() == null){
                        ///We store temporarily the last element.
                        Body temp_link = runner.getNext();
                        ///We snip the connection between the second to last and the last.
                        runner.setNext(null);
                        ///We make our runner the last element.
                        runner = temp_link;
                        break;
                    }
                    ///We then proceed to the next link.
                    runner = runner.getNext();
                }
                ///Runner now represents the last node.
                ///We must first change the location.
                runner.setLocation(head);
                ///Now we can advance head.
                head = next_pos;
                ///Now, with the location changed, we can relink it.
                runner.setNext(snipped);
                ///Finally we change what head connects to.
                next = snipped;

                //#endregion
                
            } break;
            case 'F' : {
                eating_stack = true;
            } break;
            case 'E' : {
                ///We get the next element and leave the previous one in the dark.
                next = next.getNext();
                ///We make a runner that goes into the future gangsters.
                Body runner = next;
                ///As long as we haven't gone too far out...
                while(runner != null){
                    Point dying_node_location = runner.getNext().getLocation();
                    ///We set the next node to be the one after the current next.
                    runner.setNext(runner.getNext().getNext());
                    ///We set the node's location to the previous, now dying, node's location.
                    runner.setLocation(dying_node_location);
                }
            } break;
        }
        ledraw();
        return true;
    }

    /** Uses the direction to find the character to use when drawing the head. */
    private char direction_char() {
        return switch(direction){
            case 0 -> {
                yield Driver.RIGHT;
            }
            case 1 -> {
                yield Driver.UP;
            }
            case 2 -> {
                yield Driver.LEFT;
            }
            case 3 -> {
                yield Driver.DOWN;
            }
            default -> {
                yield Driver.DOWN;
            }
        };
    }

    /** Takes a point and finds its advanced version based on the direction. */
    private static Point advanced(Point current, int direction){
        return switch(direction){
            case 0 -> {
                yield new Point(current.x + 1, current.y);
            }
            case 1 -> {
                yield new Point(current.x, current.y - 1);
            }
            case 2 -> {
                yield new Point(current.x - 1, current.y);
            }
            case 3 -> {
                yield new Point(current.x, current.y + 1);
            }
            default -> {
                yield new Point(current.x, current.y + 1);
            }
        };
    }

    /** Flips the snake so that the tail becomes the head and all the nodes change pointers to the node before them. Should be the opposite direction. */
    public void reverse(){
        ///We start with a previous following a current node.
        Body prev = null;
        Body current = next;

        while (current != null) {
            ///We temporarily store a transfer portal.
            Body nextNode = current.getNext();

            ///We reverse the link.
            current.setNext(prev);

            ///We can use our portal to move forward in both variables.
            prev = current;
            current = nextNode;
        }

        ///We now snip the link on the previously first element from the head.
        ///This makes it now the previous which is the new head.
        next = prev;
        ///Finally, we switch the direction of the head.
        direction += 2;
        direction %= 4;
    }

    /** Returns the size of the snake. */
    public int getSize(){
        int counter = 1;
        Body runner = next;
        while(runner != null){
            runner = runner.getNext();
            counter += 1;
        }
        return counter;
    }

    /** Draws the board based on the Snake's position. */
    private void ledraw(){
        ///We start by clearing the board.
        clear_board();
        ///We clamp the point of the head to fit 
        final Point head = clamped(this.head);
        final char dir_char = direction_char();
        board[head.y][head.x] = dir_char;
        ///We get a runner comb through all the points, clamp them, and draw them on the board.
        Body runner = this.next;
        while(runner != null){
            final Point clamped = clamped(runner.getLocation());
            board[clamped.y][clamped.x] = Driver.SNAKE;
            runner = runner.getNext();
        }
    }

    /** Clears the board */
    private void clear_board(){
        board = new char[10][10];
        for(var i = 0; i < 10; i++){
            for(var j = 0; j < 10; j++){
                board[i][j] = Driver.EMPTY;
            }
        }
    }

    /** Takes a point and clamps it to the standard size of the board. */
    private static Point clamped(Point p){
        return new Point(Math.max(Math.min(p.x, 9), 0), Math.max(Math.min(p.y, 9), 0));
    }

    /** Tries to get an element on the board but returns null if it cannot. */
    private Character try_get(Point p){
        try {
            return board[p.y][p.x];
        } catch (Exception _e) {
            return null;
        }
    }

    public static Body to_owned(Body b) {
        if(b == null) return null;
        return new Body(b.getLocation(), b.getNext());
    }

    public static boolean char_cmp(Character lhs, Character rhs){
        if(lhs == null || rhs == null){
            return false;
        }
        return lhs.equals(rhs);
    }
}