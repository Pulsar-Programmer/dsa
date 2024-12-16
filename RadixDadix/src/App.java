import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are intended to be read. */

public class App {
    public static void main(String[] args) throws Exception {
        ///We get the operating system so we know which operating system is being used for the path.
        final String os_name = System.getProperty("os.name").toLowerCase();
        final String path = os_name.contains("win") ? ".\\" : "./";
        
        ///We create our frame.
        JFrame foundation = new JFrame();
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        foundation.setVisible(true);
        foundation.setSize(1000, 1000);
        // foundation.setSize(NewEngland.x(100), NewEngland.y(100));
        
        RadixGraphics handle = new RadixGraphics();
        foundation.add(handle);
        
        while(true){
            
            ///We create the File Chooser.
            JFileChooser jchoose = new JFileChooser();
            jchoose.setCurrentDirectory(new File(path));
            jchoose.showOpenDialog(null);

            ///We obtain the file and process the input.
            File csv = jchoose.getSelectedFile();
            Scanner scanner = new Scanner(csv);
            // scanner.useDelimiter(",");

            // ArrayList<Integer> array = new ArrayList<>();
            // while (scanner.hasNext()) {
            //     array.add(Integer.parseInt(scanner.next()));
            // }
            // handle.setPieces(array);
            handle.start();
            foundation.repaint();

            scanner.close();










        }
    }
}
