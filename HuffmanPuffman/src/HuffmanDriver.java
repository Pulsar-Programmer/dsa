//Double comments are not intended to be read.
///Triple comments are intended to be read.

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
        



        // Scanner stdin = new Scanner(System.in);
        // // stdin.tokens().forEach(action);

        // stdin.close();
    }
}
