package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 787. Cheapest Flights Within K Stops
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * 
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. 
 * If there is no such route, output -1.
 * 
 * Example 1:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:  The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * 
 * Example 2:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 * 
 * @author WinnieZhao
 *
 */
public class CheapestFlightsWithinKStops {
    /**
     * Time Complexity: O(nlogn). n is the number of flight
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for (int[] flight : flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new ArrayList<>());
            }
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        
        // Put money, dest and stop into the PriorityQueue
        pq.add(new int[] {0, src, K+1});
        
        while (!pq.isEmpty()) {
            
            int[] curs = pq.poll();
            int amount = curs[0];
            int cur = curs[1];
            int stops = curs[2];

            if (cur == dst) {
                return amount;
            }
                
            if (stops > 0 && graph.containsKey(cur)) {
                for (int[] dests : graph.get(cur)) {
                    pq.add(new int[] {amount + dests[1], dests[0], stops-1});
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Time Complexity: O(m*k). m is the number of edges
     */
    public int findCheapestPrice_BFS(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for (int[] flight : flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new ArrayList<>());
            }
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        
        Queue<int[]> queue = new LinkedList<>();
        // add city and price as array
        queue.add(new int[] {src, 0});
        
        int price = Integer.MAX_VALUE;
        int stops = 0;
        while (!queue.isEmpty()) {
            
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                int[] curs = queue.poll();
                int cur = curs[0];
                
                if (cur == dst) {
                    price = Math.min(price, curs[1]);
                }
                
                if (graph.containsKey(cur)) {
                    for (int[] dest : graph.get(cur)) {
                        
                        if (curs[1]+dest[1] > price)  {
                            continue;
                        }
                        
                        queue.add(new int[] {dest[0], dest[1] + curs[1]});
                    }
                }
                
            }
            if (stops++ > K) {
                break;
            }
        }
        
        return price==Integer.MAX_VALUE  ? -1 : price;
    }

}
