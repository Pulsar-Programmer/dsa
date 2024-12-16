import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextArea;

public class RadixGraphics extends JPanel {
    public final static Font font = new Font("Fira Code", Font.PLAIN, 36);

    private ArrayList<Integer> pieces;
    
    public RadixGraphics(){
        pieces = new ArrayList<>();
    }
    public RadixGraphics(ArrayList<Integer> pieces){
        this.pieces = pieces;
    }
    public ArrayList<Integer> getPieces() {
        return pieces;
    }
    public void setPieces(ArrayList<Integer> pieces) {
        this.pieces = pieces;
    }
    

    public void start(){
        setBackground(new Color(0xCDA678));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D)g;
        setLayout(new FlowLayout());
        add(new TextArea());
        add(new TextArea());
        add(new TextArea());
    }




    


    
    
















    // public void MSD

}
