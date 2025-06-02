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
import proc.MouseHandler;

public class GamePanel extends JPanel implements Runnable {
    
    public Thread gameThread;
    Board board;
    Player player;
    MouseHandler handler;
    boolean turn = false;
    

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.black);
        setDoubleBuffered(true);
        handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
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

        for (var square : board.squares.select(handler.mouseX, handler.mouseY)) {
            drawer.draw_selection(turn ? App.sunglo : App.cornflowerblue, square.object.shape);
        }
        for (var wall : board.walls.select(handler.mouseX, handler.mouseY)) {
            drawer.draw_selection(turn ? App.chestnut : App.dodgerblue, wall.object.shape);
        }
        
        g2.dispose();
    }

    public void update(){
        // if(handler.mouseClicked.isPresent()){
        //     handler.mouseClicked.
        // }
        //how do you do move events in mousehandler like hovering and stuff??!? 
        //Do i have to chceck every frame? I guess so ...
        // var vec = board.squares.select(handler.mouseX, handler.mouseY);
        // for (var square : vec) {
            
        // }

    }










    



    






    // public void path_length(){

    // }





    



}