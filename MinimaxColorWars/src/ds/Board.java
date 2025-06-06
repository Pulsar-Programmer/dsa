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







    /** Gets the associated square at the given point. */
    public Optional<Square> associated_square(Point square) {
        var x_type = (square.x - 5 - 18) / (90 + 18);
        var y_type = (square.y - 5 - 18) / (90 + 18);
        var idx = x_type + y_type * 9;
        return squares.try_get(idx);
    }

    /** Gets the next wall from the current square. */
    public Optional<Wall> next_wall(Square square, boolean up, boolean right){
        // var x_offset = right ? 1 : -1;
        // var y_offset = up ? 9 : -9;


        var x_offset = right ? 90 + 1 : -1;
        var y_offset = up ? -1 : 90 + 1;
        var pos = new Point(square.object.shape.x + x_offset, square.object.shape.y + y_offset);
        var walls = this.walls.select(pos.x, pos.y);
        if(walls.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(walls.get(0));
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
