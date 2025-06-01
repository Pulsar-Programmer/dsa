package ds;

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
    
}
