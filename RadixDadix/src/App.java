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
            handle.clear();
            
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
            handle.setPieces(array);

            

            ///We must select our preferred sort type.
            String[] choices = {"MSD", "LSD"};
            String option = (String) JOptionPane.showInputDialog(null, "Sort Method", "What method should be used to sort the elements?", 
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);



            final boolean[] is_painting = {true};
            ///Spawn a second thread to periodically call repaint.
            Thread drawer = new Thread(() -> {
                while(is_painting[0]){
                    foundation.repaint();
                }
            });
            drawer.start();

            ///We choose our type of sort.
            if(option.equals("MSD")){
                handle.msd();
            } else {
                handle.lsd();
            }

            is_painting[0] = false;

            App.sleep_safe(1000);
            
            try {
                drawer.join(); // Wait for the repainting thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int choice = JOptionPane.showConfirmDialog(
                    null, // Parent component (null means no parent)
                    "Do you want to run again?", // Message to show
                    "Run Again", // Title of the dialog
                    JOptionPane.YES_NO_OPTION // Options (Yes and No buttons)
            );

            // If user clicks "Yes" (response = 0), proceed to run again
            if (choice != JOptionPane.YES_OPTION) {
                break;
            }
        }
        
    }

    ///A method to sleep safely.
    public static void sleep_safe(long amt_ms){
        try {
            Thread.sleep(amt_ms);
        } catch (Exception e) {

        }
    }
}
