//Double comments are not intended to be read.
///Triple comments are intended to be read.

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;

/** Doc comments are intended to be read. */

public class HuffmanDriver {
    public static void main(String[] args) throws Exception {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String content = Files.readString(fileChooser.getSelectedFile().toPath());
        Huffman h = new Huffman(content);
        write_output(h.encoding.toString() + "\n" + h.final_message);
        System.out.println("Encoding Successfully Created!");
        

        // Scanner stdin = new Scanner(System.in);
        // // stdin.tokens().forEach(action);

        // stdin.close();
    }
    public static void write_output(String content) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
