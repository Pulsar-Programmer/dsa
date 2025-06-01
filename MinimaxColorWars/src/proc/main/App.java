package proc.main;
import java.awt.Color;

import javax.swing.JFrame;

public class App {

    final static Color muddy_waters = new Color(0xB37E5B);
    final static Color sidecar = new Color(0xF2E0BC);
    final static Color mantle = new Color(0x8A9894);

    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Quoridor");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
    
    // public static BufferedImage res(String filepath) throws IOException{
    //     return ImageIO.read(new File(filepath));
    // }
}
