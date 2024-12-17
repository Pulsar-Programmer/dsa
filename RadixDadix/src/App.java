import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

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
        foundation.setSize(RadixGraphics.scrn_x(100), RadixGraphics.scrn_y(100));
        
        RadixGraphics handle = new RadixGraphics();
        handle.setBackground(new Color(0xCDA678));
        foundation.add(handle);
        
        // ///We create a slider for the animation speed.
        // var animation_slider = new JSlider(0, 100, 0);
        // animation_slider.setMajorTickSpacing(10);
        // animation_slider.setMinorTickSpacing(1);
        // animation_slider.setPaintTicks(true);
        // animation_slider.setPaintLabels(true);
        // foundation.add(animation_slider, BorderLayout.SOUTH);
        // handle.setVisible(true);
        // animation_slider.setVisible(true);

        while(true){
            
            ///We create the File Chooser.
            JFileChooser jchoose = new JFileChooser();
            jchoose.setCurrentDirectory(new File(path));
            jchoose.showOpenDialog(null);

            ///We obtain the file and process the input.
            File csv = jchoose.getSelectedFile();
            Scanner scanner = new Scanner(csv);
            ArrayList<Integer> array = new ArrayList<>();
            while (scanner.hasNext()) {
                array.add(Integer.parseInt(scanner.next()));
            }
            scanner.close();
            handle = new RadixGraphics(array);
            System.out.println(handle.boxes);
            // handle.setBackground(new Color(0xCDA678));
            
            ///We must select our preferred sort type.
            String[] choices = {"MSD", "LSD"};
            String option = (String) JOptionPane.showInputDialog(null, "Sort Method", "What method should be used to sort the elements?", 
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

            ///We choose our type of sort.
            if(option.equals("MSD")){
                // handle.msd();
            } else {
                // handle.lsd();
            }
            
            while (true) {
                foundation.repaint();
            }

            // ///Spawn a second thread to periodically call repaint.
            // new Thread(() -> {
            //     while(true){
            //         foundation.repaint();
            //     }
            // }).start();
        }
        
    }
}
