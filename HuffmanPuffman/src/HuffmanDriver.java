//Double comments are not intended to be read.
///Triple comments are intended to be read.

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

/** Doc comments are intended to be read. */

public class HuffmanDriver {
    public static void main(String[] args) throws Exception {

        while(true){
            String content = Files.readString(select_file());
            Huffman h = new Huffman(content);
            write_output(h.final_message);
            System.out.println("Encoding Successfully Created - Check Output!");
            System.out.println(h);

            
            System.out.println("Would you like to encode, decode, or quit? (e/d/q)");
            var scanner = new Scanner(System.in);
            if(scanner.nextLine().equals("e")){
                continue;
            } else if(scanner.nextLine().equals("d")){
                // h.decode(null, content);
            } else {
                break;
            }
            if(scanner.nextLine().equals("y")){
                // System.out.println("Enter the encoded message:");
                // String encoded_message = scanner.nextLine();
                // System.out.println("Decoded message: " + h.decode(encoded_message));
            } else {
                System.out.println("");
            }
            scanner.close();


        }
        


        

        

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
    public static Path select_file() throws Exception {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            throw new Exception("No file selected");
        }
        return fileChooser.getSelectedFile().toPath();
    }
}
