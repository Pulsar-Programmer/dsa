//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are intended to be read. */

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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
            
            

            System.out.println("Which region would you like to start at?");
            var start = scanner.nextLine();

            var risk = load_files(start);
            //input where we want to start at
            //set region thing to zero
            // risk.djikstra(new Territory())
            // risk.prim(

            System.out.println("Would you like to invade a country or show the MST?");
            System.out.println("1. Invade a country");
            System.out.println("2. Show the MST");

            var next = scanner.nextLine();
            switch (next) {
                case "1" -> {
                    System.out.println("Which country would you like to invade?");
                    var invade = scanner.nextLine();
                    System.out.println("How many soldiers would you like to send?");
                    var soldiers = scanner.nextInt(); scanner.nextLine(); // Consume the newline character left by nextInt()
                    
                    // var territory = new Territory(invade);
                    // var distance_map = risk.djikstra();
                    // System.out.println("The distance to " + invade + " is " + distance_map.get(territory));
                }
                case "2" -> {
                    System.out.println("The MST is: ");
                    // var prim_map = risk.prim_map;
                    // for(var territory : prim_map.keySet()){
                    //     System.out.println(territory.one + " to " + territory.two + " with a cost of " + prim_map.get(territory));
                    // }
                }


                default -> {}
            }

            

        
        }





        scanner.close();
    }

    /** Loads the basic files given.  */
    public static Risk load_files(String start) throws Exception{

        HashMap<Territory, HashSet<Territory>> graph = new HashMap();
        HashMap<Territory, Integer> soldiers = new HashMap();


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

            var t1 = new Territory(region_one);
            var t2 = new Territory(region_two);
            graph.computeIfAbsent(t1, k -> new HashSet<>()).add(t2);
            graph.computeIfAbsent(t2, k -> new HashSet<>()).add(t1);
        }
        scanner.close();
        
        var spath = Path.of(System.getProperty("user.dir"), "RiskSoldiers.csv");
        var scanner1 = new Scanner(spath.toFile());
        while(scanner1.hasNextLine()) {
            var result = scanner1.nextLine();
            var subscanner = new Scanner(result);
            subscanner.useDelimiter(";");

            var t = subscanner.nextLine();
            var soldier = subscanner.nextInt();
            soldiers.put(new Territory(t), soldier);
            
            subscanner.close();
        }
        scanner1.close();

        return new Risk(graph, prim_map, soldiers, new Territory(start));
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
