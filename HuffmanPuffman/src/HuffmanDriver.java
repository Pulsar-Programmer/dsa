//Double comments are not intended to be read.
///Triple comments are intended to be read.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/** Doc comments are intended to be read. */

public class HuffmanDriver {
    public static void main(String[] args) throws Exception {
        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            // System.setProperty("apple.awt.fileDialogForDirectories", "false");
            UIManager.put("FileChooser.useSystemFileDialogs", Boolean.FALSE);
        }


        ///We grab the file and encode it.
        String content = Files.readString(select_file());
        Huffman h = new Huffman(content);
        write_output(h.final_message);
        System.out.println("Encoding Successfully Created - Check `output.txt`!");
        System.out.println(h);

        ///Next, we create a scanner and start the main loop.
        var scanner = new Scanner(System.in);
        while(true){
            ///We request an encoding, decoding, or quit.
            System.out.println("Would you like to encode, decode, or quit? (e/d/q)");
            var next = scanner.nextLine();
            if(next.equals("e")){
                ///We appropriately encode.
                content = Files.readString(select_file());
                h = new Huffman(content);
                write_output(h.final_message);
                System.out.println("Encoding Successfully Created - Check `output.txt`!");
                System.out.println(h);
            } else if(next.equals("d")){
                ///We appropriately decode.
                content = Files.readString(select_file());
                var final_message = Huffman.decode(h.decoding, content);
                write_output(final_message);
                System.out.println("Decoding Successfully Created - Check `output.txt`!");
                System.out.println(h.decoding);
            } else {
                break;
            }
        }
        scanner.close();
    }
    /** This writes an output to a file called {@code output.txt} on the filesystem. */
    public static void write_output(String content) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    /** Selects a file using the java file chooser. */
    public static Path select_file() throws Exception {
        // SwingUtilities.invokeLater(() -> {
            
        // });

        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            throw new Exception("No file selected");
        }
        return fileChooser.getSelectedFile().toPath();
    }
}
