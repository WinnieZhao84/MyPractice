package LeetCode.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 815. Bus Routes
 * 
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], 
 * this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses
 * we must take to reach our destination? Return -1 if it is not possible.
 * 
 * Example:
 * 
 * Input:  routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * @author WinnieZhao
 *
 */
public class BusRoutes {
    /**
     * Time Complexity: O (number of bus stops)
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        
        if (routes == null || routes.length == 0 || routes[0].length == 0) return -1;
        if (S == T) return 0;
        
        //init the stop - buses mapping
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i=0; i<routes.length; i++) {
            for (int stop : routes[i]) {
                if (!map.containsKey(stop)) {
                    map.put(stop, new ArrayList<>());
                }
                map.get(stop).add(i);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBuses = new HashSet<>();
        visitedStops.add(S);
        
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                Integer cur = queue.poll();
                
                if (cur == T) {
                    return count;
                }
                
                List<Integer> buses = map.get(cur);
                if (buses == null || buses.isEmpty()) {
                    continue;
                }
                
                for (int bus : buses) {
                    if (visitedBuses.add(bus)) {
                        for (int stop : routes[bus]) {
                            if (visitedStops.add(stop)) {
                                queue.add(stop);
                            }
                        }
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
}
