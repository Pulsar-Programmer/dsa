import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

/**this will have a static graph of the world map as well. Hashmap would be good here along
with the methods to chart out the path */
public class Risk {
    HashMap<Territory, HashSet<Territory>> graph;
    HashMap<TerritoryTerritory, Double> prim_map;
    HashMap<Territory, Integer> soliders;

    Territory start;

    public Risk(HashMap<Territory, HashSet<Territory>> graph, HashMap<TerritoryTerritory, Double> prim_map, HashMap<Territory, Integer> soliders, Territory start) {
        this.graph = graph;
        this.prim_map = prim_map;
        this.soliders = soliders;
        this.start = start;
    }

    /** The cost between two territories is simply the cost of going to the Territory itself. */
    public double cost_dji(Territory b) {
        // return b.soldiers;
        return soliders.get(b);
    }

    public HashMap<Territory, Double> djikstra() {
        ///Create our map of distances.
        HashMap<Territory, Double> distance_map = new HashMap<>();

        ///Create a priority queue ordering the distances from each territory.
        PriorityQueue<TerritoryDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(td -> td.distance));
        ///Create a set of visited territories.
        HashSet<Territory> visited = new HashSet<>();

        for (Territory t : graph.keySet()) {
            distance_map.put(t, Double.POSITIVE_INFINITY);
        }

        distance_map.put(start, 0.0);
        pq.add(new TerritoryDistance(start, 0.0));

        while (!pq.isEmpty()) {
            TerritoryDistance current = pq.poll();
            Territory currentTerritory = current.territory;

            if (visited.contains(currentTerritory)) continue;
            visited.add(currentTerritory);

            for (Territory neighbor : graph.getOrDefault(currentTerritory, new HashSet<>())) {
                if (visited.contains(neighbor)) continue;
                
                double newDist = distance_map.get(currentTerritory) + cost_dji(neighbor);
                if (newDist < distance_map.get(neighbor)) {
                    neighbor.previous = currentTerritory;
                    distance_map.put(neighbor, newDist);
                    pq.add(new TerritoryDistance(neighbor, newDist));
                }
            }
        }

        return distance_map;
    }



    ///Tiny class to hold the territory and distance together. (I miss tuples)
    static class TerritoryDistance {
        Territory territory;
        double distance;

        TerritoryDistance(Territory territory, double distance) {
            this.territory = territory;
            this.distance = distance;
        }
    }











    public HashMap<TerritoryTerritory, Double> prim() {
        HashMap<Territory, Double> key = new HashMap<>();
        HashMap<Territory, Territory> parent = new HashMap<>();
        PriorityQueue<Territory> pq = new PriorityQueue<>(Comparator.comparingDouble(key::get));
        HashSet<Territory> inMST = new HashSet<>();

        for (Territory t : graph.keySet()) {
            key.put(t, Double.POSITIVE_INFINITY);
            parent.put(t, null);
        }

        key.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Territory u = pq.poll();

            if (inMST.contains(u)) continue;
            inMST.add(u);

            for (Territory neighbor : graph.getOrDefault(u, new HashSet<>())) {
                if (!inMST.contains(neighbor)) {
                    double weight = cost_prim(u, neighbor);
                    if (weight < key.get(neighbor)) {
                        key.put(neighbor, weight);
                        parent.put(neighbor, u);
                        pq.add(neighbor);
                    }
                }
            }
        }

        HashMap<TerritoryTerritory, Double> mstEdges = new HashMap<>();

        for (Map.Entry<Territory, Territory> entry : parent.entrySet()) {
            Territory child = entry.getKey();
            Territory par = entry.getValue();
            if (par != null) {
                double weight = cost_prim(par, child);
                mstEdges.put(TerritoryTerritory.struct(par.name, child.name), weight);
            }
        }

        return mstEdges;
    }

    private double cost_prim(Territory u, Territory neighbor) {
        return prim_map.get(TerritoryTerritory.struct(u.name, neighbor.name));
    }
}
