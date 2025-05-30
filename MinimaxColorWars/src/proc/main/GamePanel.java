package proc.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    
    public Thread gameThread;

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.black);
        setDoubleBuffered(true);
        // addKeyListener(keyH);
        // addMouseListener(l);
        setFocusable(true);
    }




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
        draw_grid(g2);
        
        g2.dispose();
    }










    public void update(){

    }










    



    /** Draws background and board. */
    public void draw_bg(Graphics2D g2) {
        g2.setColor(java.awt.Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        // Draw the board here
        // g2.drawImage(boardImage, 0, 0, null);
    }

    public static void draw_board(Graphics2D g2) {
        // Draw the board here
        // g2.drawImage(boardImage, 0, 0, null);
    }

    public void draw_grid(Graphics2D g2) {
        // g2.setColor(Color.GRAY);
        // for (int i = 0; i < this.getWidth(); i += 50) {
        //     g2.drawLine(i, 0, i, this.getHeight());
        //     g2.fillRect(i, i, WIDTH, HEIGHT);
        // }
        // for (int j = 0; j < this.getHeight(); j += 50) {
        //     g2.drawLine(0, j, this.getWidth(), j);
        // }
    }

    public void draw_player(Graphics2D g2) {
        // Draw player pieces here
        // g2.setColor(Color.RED);
        // g2.fillOval(playerX, playerY, playerWidth, playerHeight);
    }






    // public void path_length(){

    // }





    



}