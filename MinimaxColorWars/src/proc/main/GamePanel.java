package proc.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import ds.Board;
import ds.Player;
import proc.Drawing;

public class GamePanel extends JPanel implements Runnable {
    
    public Thread gameThread;
    Board board;
    Player player;

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.black);
        setDoubleBuffered(true);
        // addKeyListener(keyH);
        // addMouseListener(l);
        setFocusable(true);

        board = new Board();
        player = new Player();
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

        var drawer = new Drawing(g2);

        // drawer.draw_bg(getWidth(), getHeight());

        // drawer.draw_grid();
        
        g2.dispose();
    }










    public void update(){

    }










    



    






    // public void path_length(){

    // }





    



}