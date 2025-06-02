package proc.main;
import java.awt.Color;
import javax.swing.JFrame;

//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are intended to be read. */

public class App {

    public final static Color muddy_waters = new Color(0xB37E5B);
    public final static Color sidecar = new Color(0xF2E0BC);
    public final static Color mantle = new Color(0x8A9894);
    public final static Color dodgerblue = new Color(0x3794FF);
    public final static Color sunglo = new Color(0xE06C75);
    public final static Color cornflowerblue = new Color(0x61AFEE);
    public final static Color chestnut = new Color(0xBB4C4C);

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
