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










    /** Gets the next wall from the current square. */
    public Optional<Wall> next_wall(Square square, boolean up, boolean right){
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
        boolean is_vertical = wall.object.shape.width < wall.object.shape.height;

        var x_offset = is_vertical ? 0 : (positive ? 90 + 18 + 1 : -1 - 18);
        var y_offset = is_vertical ? (positive ? -1 - 18 : 90 + 18 + 1) : 0;

        var pos = new Point(wall.object.shape.x + x_offset, wall.object.shape.y + y_offset);
        var walls = this.walls.select(pos.x, pos.y);
        if(walls.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(walls.get(0));
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
