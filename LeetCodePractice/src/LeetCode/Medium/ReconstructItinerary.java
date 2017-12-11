package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * 
 * Note:
 * 
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
 * order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * 
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * 
 * Example 1: 
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * 
 * Example 2:
 * 
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * 
 * @author WinnieZhao
 *
 */
public class ReconstructItinerary {

    List<String> result = new ArrayList<>();
    int numTickets;
    
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) {
            return result;
        }
        
        numTickets = tickets.length;
        
        Map<String, List<String>> ticketsMap = new HashMap<>();

        Map<String, List<String>> ticketMap = new HashMap<>();
        for (String[] ticket : tickets) {
            ticketMap.compute(ticket[0], (k, v) -> v == null ? new ArrayList<>() : v).add(ticket[1]);
        }

        for (Map.Entry<String, List<String>> entry : ticketsMap.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        result.add("JFK");
        this.dfs(ticketsMap, "JFK");
        
        return result;
        
    }
    
    private void dfs(Map<String, List<String>> ticketsMap, String from) {
        if (result.size() == numTickets+1) {
            return;
        }
        if (!ticketsMap.containsKey(from)) {
            return;
        }
        
        List<String> list = ticketsMap.get(from);
        
        for (int i = 0; i < list.size(); i++) {
            String neighbor = list.get(i);
            
            list.remove(i);
            result.add(neighbor);
            
            this.dfs(ticketsMap, neighbor);

            if (result.size() == numTickets+1) {
                return;
            }
            
            list.add(i, neighbor);
            result.remove(result.size()-1);
        }
    }
    
    public static void main(String[] args) {
        ReconstructItinerary solution = new ReconstructItinerary();
        
        String[][] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        
        System.out.println(solution.findItinerary(tickets));
    }
}
