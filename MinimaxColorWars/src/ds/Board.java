package ds;

import java.util.Optional;
import java.awt.Point;

/** Represents the physical board. */
public class Board {
    //must allow clicking in the spaces
    //have all walls present, make them eitehr disabled or enabled

    public SelectPool<Wall> walls;
    public SelectPool<Square> squares;

    public Board(SelectPool<Wall> walls, SelectPool<Square> squares){
        this.walls = walls;
        this.squares = squares;
    }

    public Board() {
        this(SelectPool.Default(), SelectPool.Default());
    }






    
    /** Checks if the move is blocked by a wall. */
    // public boolean isMoveBlocked(int r, int c, int dir) {
    //     var idx = 9 * r + c;
    //     // next_wall(null, false, false);
    //     // return walls[r][c][dir];
    // }

    /** Gets the associated square at the given point. */
    public Optional<Square> associated_square(Point square) {
        var x_type = (square.x - 5 - 18) / (90 + 18);
        var y_type = (square.y - 5 - 18) / (90 + 18);
        var idx = x_type + y_type * 9;
        return squares.try_get(idx);
    }

    /** Gets the next wall from the current square. */
    public Optional<Wall> next_wall(Square square, boolean axis, boolean positive){

        var x_type = square.col();
        var y_type = square.row();

        var b_type = axis; // true for vertical, false for horizontal

        var idx = 0;

        if (b_type) {
            // Vertical wall (placed to the left or right of square)
            if (positive) {
                x_type += 1; // place to the right
            } else {
                // x_type -= 1; // place to the left
            }
            if (x_type < 0 || x_type > 9) return Optional.empty(); // invalid wall position
            if (y_type < 0 || y_type > 9) return Optional.empty();
            idx = x_type + y_type * 10 + 99;
        } else {
            // Horizontal wall (placed above or below square)
            if (positive) {
                // y_type -= 1; // place above
            } else {
                y_type += 1; // place below
            }
            if (x_type < 0 || x_type > 9) return Optional.empty();
            if (y_type < 0 || y_type > 9) return Optional.empty(); // must be within board
            idx = x_type + y_type * 10;
        }

        return walls.try_get(idx);
    }
    /** Finds the wall adjacent to the current wall. */
    public Optional<Wall> adjacent_wall(Wall wall, boolean positive){
        boolean is_vertical = wall.is_vertical();

        var x_offset = is_vertical ? 0 : 1;
        var y_offset = is_vertical ? 10 : 0;
        x_offset *= positive ? 1 : -1;
        y_offset *= positive ? 1 : -1;
        
        var next = walls.try_get(wall.map() + x_offset + y_offset);

        if(next.isPresent()){
            var map = next.get().map();
            if(!is_vertical){
                if(map % 10 == 0 || map % 10 == 8 || map % 10 == 9 || map > 99) {
                    next = Optional.empty();
                }
            } else{
                if(map < 99){
                    next = Optional.empty();
                }
            }
        }

        return next;
    }
    /** Finds the parent square given the wall. */
    public Optional<Square> parent_square(Wall wall, boolean positive){

        boolean is_vertical = wall.object.shape.width < wall.object.shape.height;

        var x_offset = is_vertical ? (positive ? 1 + 18 : -1) : 0;
        var y_offset = is_vertical ? 0 : (positive ? -1 : 1 + 18);

        var pos = new Point(wall.object.shape.x + x_offset, wall.object.shape.y + y_offset);
        var squares = this.squares.select(pos.x, pos.y);
        if(squares.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(squares.get(0));
    }
    /** Gets the next square from the current square. */
    public Optional<Square> next_square(Square square, boolean up, boolean right){
        //TODO this is broken
        var x_offset = right ? 90 + 1 + 18 : -1 - 18;
        var y_offset = up ? -1 - 18 : 90 + 1 + 18;
        var pos = new Point(square.object.shape.x + x_offset, square.object.shape.y + y_offset);
        var squares = this.squares.select(pos.x, pos.y);
        if(squares.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(squares.get(0));
    }
    
}
