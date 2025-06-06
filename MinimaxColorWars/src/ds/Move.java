package ds;

public class Move {
    public enum Type { PAWN, WALL }

    Type type;
    Square to;
    Wall wall1;
    Wall wall2;

    private Move() {

    }

    public static Move pawn(Square square) {
        var move = new Move();
        move.type = Type.PAWN;
        move.to = square;
        return move;
    }

    public static Move wall(Wall one, Wall two) {
        var move = new Move();
        move.type = Type.PAWN;
        move.wall1 = one;
        move.wall2 = two;
        return move;
    }
}

