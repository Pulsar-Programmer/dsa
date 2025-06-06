package proc.main;
import ds.Board;
import ds.Player;
import ds.Selectable;
import ds.Square;
import ds.Wall;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JPanel;
import proc.Drawing;
import proc.MouseHandler;

public class GamePanel extends JPanel implements Runnable {
    
    public Thread gameThread;
    Board board;
    Player main;
    Player op;
    MouseHandler handler;
    boolean turn = false;
    Optional<Wall> selecting = Optional.empty();
    boolean ai;
    

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(Color.black);
        setDoubleBuffered(true);
        handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
        setFocusable(true);
    }

    /** Starts running the game thread. */
    public void startGameThread() {
        // sounds.clear();
        // sounds.addFile(12);
        // sounds.loop();
        setupGame();
        gameThread = new Thread(this);
        gameThread.start();
    }

    /** Gets the game ready. */
    public void setupGame(){
        main = Player.main();
        op = Player.opponent();
        // main.translate(5, 0);
        ai = false;
        setupBoard();
        // setupPlayers();
    }

    /** Sets up the board to get ready the game. */
    public void setupBoard(){
        board = new Board();
        var def_xy = 5 + 18;

        var color = false;

        for(var i = 0; i < 9; i++){
            for(var j = 0; j < 9; j++){
                var pos = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * j);
                board.squares.push(color ? Square.muddy_waters(pos) : Square.sidecar(pos));

                var pos_top = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * j - 18);
                var top = Wall.wide(pos_top);
                board.walls.push(top);

                var pos_left = new Point(def_xy + (90 + 18) * i - 18, def_xy + (90 + 18) * j);
                var left = Wall.high(pos_left);
                board.walls.push(left);

                color = !color;
            }
        }

        for(var i = 0; i < 9; i++){
            var pos_bottom = new Point(def_xy + (90 + 18) * i, def_xy + (90 + 18) * 9 - 18);
            var bottom = Wall.wide(pos_bottom);
            board.walls.push(bottom);

            var pos_right = new Point(def_xy + (90 + 18) * 9 - 18, def_xy + (90 + 18) * i);
            var right = Wall.high(pos_right);
            board.walls.push(right);
        }
    }

    /** Runs the game thread. */
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
        drawer.draw_object(main.object);
        drawer.draw_object(op.object);

        for (var square : board.squares.select(handler.mouseX, handler.mouseY)) {
            drawer.draw_selection(turn ? App.sunglo : App.cornflowerblue, square.object.shape);
        }
        for (var wall : board.walls.select(handler.mouseX, handler.mouseY)) {
            drawer.draw_selection(turn ? App.chestnut : App.dodgerblue, wall.object.shape);
        }
        
        g2.dispose();
    }

    /**Updates the game called once per frame. */
    public void update(){
        
        if(ai && !turn){
            ai_update();
            return;
        }
        // if(true){
        //     for (var square : board.squares.select(handler.mouseX, handler.mouseY)) {
        //         message(square.map() + "");
        //     }
        //     for (var wall : board.walls.select(handler.mouseX, handler.mouseY)) {
        //         message(wall.map() + "");
        //     }
        // }
        if(selecting.isPresent()){
            if(handler.mouseClicked.isPresent()){
                var p = handler.mouseClicked.get();

                var walls = board.walls.select(p.x, p.y);
                if(!walls.isEmpty()){
                    Wall wall = walls.get(0);
                    if(wall.enabled){
                        message("Wall already enabled");
                        handler.mouseClicked = Optional.empty();
                        return;
                    }

                    ArrayList<Optional<Wall>> arr = new ArrayList<>();
                    arr.add(board.adjacent_wall(wall, false));
                    arr.add( board.adjacent_wall(wall, true));
                    if(!check_among(arr, selecting.get())){
                        message("Wall must be next to the previous one!");
                        handler.mouseClicked = Optional.empty();
                        return;
                    }

                    wall.enable();
                    selecting = Optional.empty();
                    turn = !turn;
                }
                handler.mouseClicked = Optional.empty();
            }
        }
        if(handler.mouseClicked.isPresent()){
            var p = handler.mouseClicked.get();

            var squares = board.squares.select(p.x, p.y);
            if(!squares.isEmpty()){
                // var square = squares.get(0);
                turn = !turn;
                return;
            }
            var walls = board.walls.select(p.x, p.y);
            if(!walls.isEmpty()){
                Wall wall = walls.get(0);
                if(wall.enabled){
                    message("Wall already enabled.");
                    return;
                }
                wall.enable();
                selecting = Optional.of(wall);
                message("Select another wall.");
            }
            handler.mouseClicked = Optional.empty();
        }
        

    }

    /** Helper function to check the squares or selectables around the area. */
    public <T extends Selectable> boolean check_among(ArrayList<Optional<T>> a, T ctn){
        var arr = new ArrayList<Integer>();
        for (Optional<T> t : a) {
            if(t.isPresent()) {
                var val = t.get().map();
                // message("" + val);
                arr.add(val);
            }
        }
        return arr.contains(ctn.map());
    }

    /** Messages the user. */
    public void message(String msg){
        System.out.println("\n" + msg);
    }










    //#region AI

   

    /** Updates the ai - called every frame for the ai. */
    public void ai_update(){
        //TODO
    }



    // public void path_length(){

    // }





    


    //#endregion AI
}