import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class HashiDriver {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) { 
            System.out.println("Would you like to insert a graph or break?");
            System.out.println("1. Insert a Graph");
            System.out.println("2. Break");
            if(scanner.nextLine().equals("1")){
                System.out.println("Please select a file to insert a graph from.");
                Path path = select_file();
                System.out.println("Inserting graph from " + path.toString() + "...");
                var file = path.toFile();
                var fileString = new String(java.nio.file.Files.readAllBytes(path));
                var hashi = Hashi.load_from_str(fileString);
                System.out.println(hashi);
                System.out.println(hashi.determine_if_solved());
                System.out.println("_______________________________");
            } else {
                break;
            }
        }




        scanner.close();
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
