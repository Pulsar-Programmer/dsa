package proc;

import ds.Board;
import ds.GraphicsObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class Drawing {
    Graphics2D g2;

    public Drawing(Graphics2D g2) {
        this.g2 = g2;
    }

    /** Draws background. */
    public void draw_bg() {
        final var dim = 990;
        
        g2.setColor(java.awt.Color.black);
        g2.fillRect(5, 5, dim + 5, dim + 5);
    }

    /**Draws the board given its components. */
    public void draw_board(Board board) {
        board.squares.forEach((square) -> square.object.draw(g2));
        board.walls.forEach((wall) -> wall.object.draw(g2));
    }

    /** Draws a selection on an object. */
    public void draw_selection(Color shade, Shape selection){
        var prev = g2.getStroke();

        g2.setColor(shade);
        g2.setStroke(new BasicStroke(5));
        g2.draw(selection);

        g2.setStroke(prev);
    }

    /** Draws the object. */
    public <T extends Shape> void draw_object(GraphicsObject<T> obj){
        obj.draw(g2);
    }









    // public void drawPawn(Graphics2D g2d) {
    //     // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //     g2d.setColor(Color.BLACK);

    //     int centerX = 150;
    //     int baseY = 250;

    //     // Draw head (oval)
    //     int headWidth = 60;
    //     int headHeight = 60;
    //     g2d.fillOval(centerX - headWidth / 2, baseY - 200, headWidth, headHeight);

    //     // Draw neck (rectangle)
    //     int neckWidth = 20;
    //     int neckHeight = 60;
    //     g2d.fillRect(centerX - neckWidth / 2, baseY - 140, neckWidth, neckHeight);

    //     // Draw base (polygon like hex base)
    //     Polygon base = new Polygon();
    //     base.addPoint(centerX - 50, baseY);       // Bottom left
    //     base.addPoint(centerX - 30, baseY - 30);  // Upper left
    //     base.addPoint(centerX - 10, baseY - 40);  // Neck left
    //     base.addPoint(centerX + 10, baseY - 40);  // Neck right
    //     base.addPoint(centerX + 30, baseY - 30);  // Upper right
    //     base.addPoint(centerX + 50, baseY);       // Bottom right
    //     g2d.fillPolygon(base);
    // }
}
