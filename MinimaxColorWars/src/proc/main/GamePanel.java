package proc.main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    



    public Thread gameThread;

    public void startGameThread() {
        // sounds.clear();
        // sounds.addFile(12);
        // sounds.loop();
        setupGame();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){
        
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta -= 1;
            }
        } 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        draw_bg(g2);

        
        g2.dispose();
    }










    public void update(){

    }



    /** Draws background and board. */
    public void draw_bg(Graphics2D g2) {
        
    }





    



}