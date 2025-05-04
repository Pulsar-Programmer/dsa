//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are intended to be read. */

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * this takes in the territories between which the shortest path can be found as well as
the shortest that is possible.
 */
public class RiskDriver {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            

            System.out.println("Would you like to start a game or break?");
            System.out.println("1. Start a Game");
            System.out.println("2. Quit");

            if(!scanner.nextLine().equals("1")){
                break;
            }
            //input where we want to start at

            System.out.println("Which region would you like to start at?");

            //set region thing to zero


            

            




        }


        
        ArrayList<Integer> array = new ArrayList<>();
        while (scanner.hasNext()) {
            array.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();





        
    }

    /** Loads the basic files given.  */
    public static Risk load_files() throws Exception{

        HashMap<TerritoryTerritory, Double> prim_map = new HashMap();
        var path = Path.of(System.getProperty("user.dir"), "Terrain.csv");
        var scanner = new Scanner(path.toFile());
        while(scanner.hasNextLine()) {
            var result = scanner.nextLine();
            var subscanner = new Scanner(result);
            subscanner.useDelimiter(";");
            var region_one = subscanner.nextLine();
            var region_two = subscanner.nextLine();
            var territorysquare = TerritoryTerritory.struct(region_one, region_two);
            prim_map.put(territorysquare, subscanner.nextDouble());
            subscanner.close();
        }
        scanner.close();
        
        HashMap<Territory, HashSet<Territory>> graph = new HashMap();
        var soldiers = Path.of(System.getProperty("user.dir"), "RiskSoldiers.csv");
        var scanner1 = new Scanner(soldiers.toFile());
        while(scanner1.hasNextLine()) {
            var result = scanner1.nextLine();
            var subscanner = new Scanner(result);
            subscanner.useDelimiter(";");

            var t1 = subscanner.nextLine();
            var t2 = subscanner.nextLine();
            // graph.computeIfAbsent(t1, k -> new HashSet<>());
            
            subscanner.close();
        }
        scanner1.close();

        return new Risk(graph, prim_map);
    }

    // /** Selects a file using the java file chooser. */
    // public static Path select_file() throws Exception {
    //     JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    //     if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
    //         throw new Exception("No file selected.");
    //     }
    //     return fileChooser.getSelectedFile().toPath();
    // }
}
