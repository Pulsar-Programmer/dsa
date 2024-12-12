import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        
        JFileChooser jchoose = new JFileChooser();
        jchoose.setCurrentDirectory(new File(".\\"));
        jchoose.showOpenDialog(null);

        File csv = jchoose.getSelectedFile();

        var array = new ArrayList();

        Scanner scanner = new Scanner(csv);
        
        scanner.close();
        
        JFrame frame = new JFrame();
        
        
    }
}
