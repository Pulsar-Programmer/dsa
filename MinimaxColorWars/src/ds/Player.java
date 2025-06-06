package ds;

import java.awt.Point;
import java.awt.Polygon;

import proc.main.App;

public class Player {
    public GraphicsObject<Polygon> object;
    
    private Player() {
        var p = generate_polygon();
        object = new GraphicsObject<Polygon>(App.mantle, p);
    }

    /** Main player default. */
    public static Player main(){
        var p = new Player();
        p.object.color = App.sunglo;
        p.translate(4, 0);
        return p;
    }

    /** Opponent player default. */
    public static Player opponent(){
        var p = new Player();
        p.object.color = App.dodgerblue;
        p.translate(4, 8);
        return p;
    }

    /**Generates the Player polygon. */
    public static Polygon generate_polygon(){
        int centerX = 5 + 18 + 45;
        int centerY = 5 + 18 + 45;
        int size = 90;

        double radius = size / 2.0;

        var octagon = new Polygon();

        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(45 * i - 22.5);
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            octagon.addPoint(x, y);
        }

        return octagon;
    }

    /** Translates the object. */
    public void translate(int x, int y){
        object.shape.translate(x * 108, y * 108);
    }

    /** Associated square. */
    public Square associated_square(Board board){
        return board.associated_square(object.shape.getBounds().getLocation()).get();
    }

}
