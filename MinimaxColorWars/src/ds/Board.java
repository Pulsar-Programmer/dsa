package ds;

/** Represents the physical board. */
public class Board {
    //must allow clicking in the spaces
    //have all walls present, make them eitehr disabled or enabled

    SelectPool<Wall> walls;
    SelectPool<Square> squares;

    public Board(SelectPool<Wall> walls, SelectPool<Square> squares){

    }

    public Board() {
        walls = SelectPool.Default();
        squares = SelectPool.Default();
    }
    
}
