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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import javax.swing.JPanel;
import proc.Drawing;
import proc.MouseHandler;

public class GamePanel extends JPanel implements Runnable {
    
    public Thread gameThread;
    Board board = new Board();
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
            // drawer.draw_selection(App.mantle, board.next_wall(square, false, false).orElse(board.walls.try_get(0).get()).object.shape);
            // drawer.draw_selection(App.mantle, board.next_wall(square, true, false).orElse(board.walls.try_get(0).get()).object.shape);
            // drawer.draw_selection(App.mantle, board.next_wall(square, false, true).orElse(board.walls.try_get(0).get()).object.shape);
            // drawer.draw_selection(App.mantle, board.next_wall(square, true, true).orElse(board.walls.try_get(0).get()).object.shape);
        }
        for (var wall : board.walls.select(handler.mouseX, handler.mouseY)) {
            drawer.draw_selection(turn ? App.chestnut : App.dodgerblue, wall.object.shape);
        }
        
        g2.dispose();
    }

    /**Updates the game called once per frame. */
    public void update(){
        var won = has_won();
        if(won.isPresent()){
            message("Player " + (won.get() ? 1 : 2) + "has won!");
        }
        
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
                handler.mouseClicked = Optional.empty();

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
                        return;
                    }

                    wall.enable();
                    selecting = Optional.empty();
                    turn = !turn;
                }
            }
        }
        if(handler.mouseClicked.isPresent()){
            var p = handler.mouseClicked.get();
            handler.mouseClicked = Optional.empty();

            var squares = board.squares.select(p.x, p.y);
            if(!squares.isEmpty()){
                var square = squares.get(0);
                var player = (!turn ? main : op);
                var player_square = player.associated_square(board);

                var dif = square.map() - player_square.map();
                // message("" + square.map() + " " + player_square.map());
                if(Math.abs(dif) != 9 && Math.abs(dif) != 1){
                    message("Player cannot move to that square!");
                    return;
                }

                var op_square = (turn ? main : op).associated_square(board);
                if(op_square.map() == square.map()){
                    message("Player cannot move to that square!");
                    return;
                }

                if(!areSquaresAdjacent(square.map(), player_square.map())){
                    message("Player cannot move to that square!");
                    return;
                }

                var y = Math.abs(dif) == 9;
                var positive = dif > 0;
                if(board.next_wall(player_square, !y, y ? !positive : positive).map(x -> x.enabled).orElse(false)){
                    message("Player is blocked!");
                    return;
                }
                
                player.translate((y ? 0 : 1) * (positive ? 1 : -1), (y ? 1 : 0) * (positive ? 1 : -1));

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
                //TODO cannot place to box in the boi
                //TODO you can cause a softlock by clicking certain vrick
                wall.enable();
                selecting = Optional.of(wall);
                message("Select another wall.");
            }
        }
        

    }








    /** Checks who has won. */
    public Optional<Boolean> has_won(){
        var p = main.associated_square(board).row();
        var p2 = op.associated_square(board).row();
        if(p == 0){
            return Optional.of(true);
        }
        if(p2 == 9){
            return Optional.of(false);
        }

        return Optional.empty();
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

    /** Checks if squares are adjacent. */
    public static boolean areSquaresAdjacent(int index1, int index2) {
        int x1 = index1 % 9, y1 = index1 / 9;
        int x2 = index2 % 9, y2 = index2 / 9;

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        return (dx + dy == 1);
    }






    //#region AI

   

    /** Updates the ai - called every frame for the ai. */
    public void ai_update(){
        //TODO
    }

    /** Evaluates the board state for the AI. 
     * Returns a score where a lower score is better for player 1.
     * Player 1 wants to reach row 8, and player 2 wants to reach row 0.
     */
    // public static int evaluate(Board board, Player p1, Player p2) {
    //     int pathP1 = shortestPathLength(board, p1, 8); // P1 wants to reach row 8
    //     int pathP2 = shortestPathLength(board, p2, 0); // P2 wants to reach row 0

    //     // A lower score is better for player 1
    //     return pathP2 - pathP1;
    // }


    // Direction vectors: up, down, left, right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // /** Finds the shortest path length in the board. */
    // private static int shortestPathLength(Board board, Player player, int goalRow) {
    //     Queue<int[]> queue = new LinkedList<>();
    //     boolean[][] visited = new boolean[9][9];
    //     var psquare = player.associated_square(board);
    //     queue.add(new int[]{psquare.row(), psquare.col(), 0});
    //     visited[psquare.row()][psquare.col()] = true;

    //     while (!queue.isEmpty()) {
    //         int[] current = queue.poll();
    //         int r = current[0], c = current[1], dist = current[2];

    //         if (r == goalRow) return dist;

    //         for (int dir = 0; dir < 4; dir++) {
    //             int[] d = DIRECTIONS[dir];
    //             //new row and column based on direction
    //             int nr = r + d[0], nc = c + d[1];
    //             if (in_bounds(nr, nc) && !visited[nr][nc] && !board.isMoveBlocked(r, c, dir)) {
    //                 visited[nr][nc] = true;
    //                 queue.add(new int[]{nr, nc, dist + 1});
    //             }
    //         }
    //     }

    //     // If no path is found (which shouldn't happen in valid Quoridor games), return a large value
    //     return Integer.MAX_VALUE;
    // }

    // /** Checks if the given row and column are within the bounds of the board. */
    // public static boolean in_bounds(int row, int col){
    //     return !(row < 0 || row >= 9 || col < 0 || col >= 9);
    // }









    // class Move {
    //     enum Type { MOVE, WALL }
    //     Type type;
    //     int row, col, dir; // For wall placement
    //     int newRow, newCol; // For pawn move
    // }


    // public static int alphaBeta(Board board, Player p1, Player p2, int depth, int alpha, int beta, boolean maximizingPlayer) {
    //     if (depth == 0 || isGameOver(p1, p2)) {
    //         return evaluate(board, p1, p2);
    //     }

    //     List<Move> legalMoves = generateLegalMoves(board, maximizingPlayer ? p1 : p2);

    //     if (maximizingPlayer) {
    //         int maxEval = Integer.MIN_VALUE;
    //         for (Move move : legalMoves) {
    //             applyMove(board, move, p1, p2);
    //             int eval = alphaBeta(board, p1, p2, depth - 1, alpha, beta, false);
    //             undoMove(board, move, p1, p2);
    //             maxEval = Math.max(maxEval, eval);
    //             alpha = Math.max(alpha, eval);
    //             if (beta <= alpha) break; // β cut-off
    //         }
    //         return maxEval;
    //     } else {
    //         int minEval = Integer.MAX_VALUE;
    //         for (Move move : legalMoves) {
    //             applyMove(board, move, p1, p2);
    //             int eval = alphaBeta(board, p1, p2, depth - 1, alpha, beta, true);
    //             undoMove(board, move, p1, p2);
    //             minEval = Math.min(minEval, eval);
    //             beta = Math.min(beta, eval);
    //             if (beta <= alpha) break; // α cut-off
    //         }
    //         return minEval;
    //     }
    // }



    //#endregion AI
}