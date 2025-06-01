package proc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import ds.Board;
import ds.GraphicsObject;

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

    public void draw_board(Board board) {
        board.squares.pool.forEach((square) -> square.object.draw(g2));
        board.walls.pool.forEach((wall) -> wall.object.draw(g2));
        // Draw the board here
        // g2.drawImage(boardImage, 0, 0, null);
        //TODO
    }

    /** Draws a selection on an object. */
    public void draw_selection(Color shade, Shape selection){
        var prev = g2.getStroke();

        g2.setColor(shade);
        g2.setStroke(new BasicStroke(4));
        g2.draw(selection);

        g2.setStroke(prev);
    }

    /** Draws the object. */
    public <T extends Shape> void draw_object(GraphicsObject<T> obj){
        obj.draw(g2);
    }
}
