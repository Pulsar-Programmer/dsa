package proc;

import java.awt.Graphics2D;
import java.awt.Shape;

import ds.GraphicsObject;

public class Drawing {
    Graphics2D g2;

    public Drawing(Graphics2D g2) {
        this.g2 = g2;
    }






    

    /** Draws background. */
    public void draw_bg(int width, int height) {
        
        // g2.setColor(java.awt.Color.black);
        // g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        // Draw the board here
        // g2.drawImage(boardImage, 0, 0, null);
    }

    public void draw_board() {
        // Draw the board here
        // g2.drawImage(boardImage, 0, 0, null);
    }

    public void draw_grid() {
        // g2.setColor(Color.GRAY);
        // for (int i = 0; i < this.getWidth(); i += 50) {
        //     g2.drawLine(i, 0, i, this.getHeight());
        //     g2.fillRect(i, i, WIDTH, HEIGHT);
        // }
        // for (int j = 0; j < this.getHeight(); j += 50) {
        //     g2.drawLine(0, j, this.getWidth(), j);
        // }
    }

    public void draw_player() {
        // Draw player pieces here
        // g2.setColor(Color.RED);
        // g2.fillOval(playerX, playerY, playerWidth, playerHeight);
    }

    public <T extends Shape> void draw_object(GraphicsObject<T> obj){
        obj.draw(g2);
    }
}
