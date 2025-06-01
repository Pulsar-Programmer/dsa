package proc.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import ds.Board;
import ds.Player;
import ds.Square;
import ds.Wall;
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
        setupBoard();
        // setupPlayers();
    }

    public void setupBoard(){
        board = new Board();
        var def_xy = 5 + 18;

        var color = false;

        for(var i = 0; i < 9; i++){
            for(var j = 0; j < 9; j++){
                var pos = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * j);
                board.squares.pool.add(color ? Square.muddy_waters(pos) : Square.sidecar(pos));

                var pos_top = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * j - 18);
                var top = Wall.wide(pos_top);
                board.walls.pool.add(top);

                var pos_left = new Point(def_xy + (90 + 18) * i - 18, def_xy + (90 + 18) * j);
                var left = Wall.high(pos_left);
                board.walls.pool.add(left);

                color = !color;
            }
            // color = !color;
        }

        for(var i = 0; i < 9; i++){
            var pos_bottom = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * 9 - 18);
            var bottom = Wall.wide(pos_bottom);
            board.walls.pool.add(bottom);

            var pos_right = new Point(def_xy + (90 + 18) * 9 - 18, def_xy + (90 + 18) * i);
            var right = Wall.high(pos_right);
            board.walls.pool.add(right);
        }
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

        drawer.draw_bg();
        drawer.draw_board(board);

        // drawer.draw_grid();
        
        g2.dispose();
    }










    public void update(){

    }










    



    






    // public void path_length(){

    // }





    



}