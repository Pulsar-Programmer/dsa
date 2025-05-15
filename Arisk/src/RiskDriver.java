//Double comments are not intended to be read.
///Triple comments are intended to be read.
/** Doc comments are intended to be read. */

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * this takes in the territories between which the shortest path can be found as well as
the shortest that is possible.
 */
public class RiskDriver {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            ///Query the start of the game.
            System.out.println("Would you like to start a game or break?");
            System.out.println("1. Start a Game");
            System.out.println("2. Quit");

            if(!scanner.nextLine().equals("1")){
                break;
            }

            ///Query the start and load in the files.
            System.out.println("Which region would you like to start at?");
            var start = scanner.nextLine();

            var risk = load_files(start);

            System.out.println("Would you like to invade a country or show the MST?");
            System.out.println("1. Invade a country");
            System.out.println("2. Show the MST");

            var next = scanner.nextLine();
            switch (next) {
                case "1" -> {
                    var dmap = risk.djikstra();

                    Map<String, Double> distanceMap = dmap.entrySet().stream()
                    .collect(Collectors.toMap(
                        entry -> entry.getKey().name,
                        Map.Entry::getValue
                    ));

                    while (true) {
                        System.out.println("Which country would you like to invade OR type `quit`?");
                        var invade = scanner.nextLine();
                        if(invade.equals("quit")) break;
                        var dist = distanceMap.get(invade);
                        System.out.println("The distance to " + invade + " is " + dist);

                        System.out.println("How many soldiers would you like to send?");
                        var soldiers = scanner.nextInt(); scanner.nextLine();

                        ///Find the last node.
                        Territory last = null;
                        for (Territory territory : dmap.keySet()) {
                            if (territory.name.equals(invade)) {
                                last = territory;
                            }
                        }
                        
                        ///Retrace the path.
                        Stack<Territory> path = new Stack();
                        var newdmap = risk.previous_map;
                        while (last != null) {
                            path.push(last);
                            last = newdmap.get(last);
                        }

                        StringBuilder pathStr = new StringBuilder();
                        ///We use this previous distance to track the previous distance of the previous node.
                        var prevdist = 0d;
                        while (!path.isEmpty()) {
                            ///We get what is popped.
                            var popped = path.pop();
                            pathStr.append(popped.name);
                            ///The soldiers die after a distance.
                            var distt = distanceMap.get(popped.name);
                            soldiers -= distt - prevdist;
                            prevdist = distt;
                            //reusing `last` variable
                            if(soldiers <= 0) {
                                last = popped;
                                break;
                            }
                            if (!path.isEmpty()) {
                                pathStr.append(" -> ");
                            }
                        }
                        System.out.println("The path is: " + pathStr);

                        if(soldiers >= 0) System.out.println("Success!");
                        else System.out.println("Failed at " + last.name + "!");
                    }
                }
                case "2" -> {
                    System.out.println("The MST is: ");
                    var runner = 0;
                    var entries = risk.prim().entrySet();
                    for (var entry : entries) {
                        var t = entry.getKey();
                        var money = entry.getValue();
                        System.out.println(t.one + " -> " + t.two + " : " + money);
                        runner += money;
                    }
                    System.out.println("The least value is " + runner);
                }

                default -> {}
            }

            

        
        }





        scanner.close();
    }

    /** Loads the basic files given.  */
    public static Risk load_files(String start) throws Exception{

        HashMap<Territory, HashSet<Territory>> graph = new HashMap();
        HashMap<String, Integer> soldiers = new HashMap();
        HashMap<TerritoryTerritory, Double> prim_map = new HashMap();

        var path = Path.of(System.getProperty("user.dir"), "Terrain.csv");
        var scanner = new Scanner(path.toFile());
        while(scanner.hasNextLine()) {
            var result = scanner.nextLine();
            var subscanner = new Scanner(result);
            subscanner.useDelimiter(";");
            var region_one = subscanner.next().replace("\uFEFF", "").trim();
            var region_two = subscanner.next().replace("\uFEFF", "").trim();
            var territorysquare = TerritoryTerritory.struct(region_one, region_two);
            var territorysquare2 = TerritoryTerritory.struct(region_two, region_one);
            var dubble = subscanner.nextDouble();
            prim_map.put(territorysquare, dubble);
            prim_map.put(territorysquare2, dubble);
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

            var t = subscanner.next().replace("\uFEFF", "").trim();
            var soldier = subscanner.nextInt();
            soldiers.put(t, soldier);
            
            subscanner.close();
        }
        scanner1.close();
        return new Risk(graph, prim_map, soldiers, new Territory(start));
    }
}
