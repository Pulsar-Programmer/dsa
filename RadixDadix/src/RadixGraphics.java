import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

public class RadixGraphics extends JPanel {
    public final static Font font = new Font("Fira Code", Font.PLAIN, 36);

    private ArrayList<Integer> pieces;
    private ArrayList<Box> boxes;
    private ArrayList<RadixHolder> holders;
    private ArrayList<Pointer> pointers;
    
    public RadixGraphics(){
        pieces = new ArrayList<>();
        boxes = new ArrayList<>();
        holders = new ArrayList<>();
        pointers = new ArrayList<>();
    }
    public RadixGraphics(ArrayList<Integer> pieces){
        this();
        this.pieces = pieces;
    }


    public ArrayList<Integer> getPieces() {
        return pieces;
    }
    public void setPieces(ArrayList<Integer> pieces) {
        this.pieces = pieces;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D)g;
        g2d.setFont(font);
        placeArray(g2d, new Point(100, 100));
    }







    private void placeArray(Graphics2D ctx, Point p){
        var list = pieces;

        FontMetrics fm = ctx.getFontMetrics();
        int yOffset = fm.getAscent();
        int x = p.x;
        int y = p.y + yOffset;
        
        for(var i = 0; i < list.size(); i++){
            var to_draw = list.get(i).toString();
            var x_offset = fm.stringWidth(to_draw);

            var handle = new Box(x, y, x_offset + 10, yOffset + 5, to_draw);
            handle.draw(ctx, yOffset);
            boxes.add(handle);

            x += x_offset + 20;
        }
    }




    


    
    









    // public void MSD


    
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }
}
