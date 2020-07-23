package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 785. Is Graph Bipartite?
 * Given an undirected graph, return true if and only if it is bipartite.
 * 
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  
 * Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * 
 * Example 1:
 * 
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * 
 * @author WinnieZhao
 *
 */
public class IsGraphBipartite {

    /**
     * BFS solution
     */
    public boolean isBipartite(int[][] graph) {
        
        int[] color = new int[graph.length];
            
        for (int i=0; i<graph.length; i++) {
         
            if (color[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            color[i] = 1; // Blue: 1; Red: -1.
            
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                
                for (int node : graph[cur]) {
                    if (color[node] == 0) {
                        color[node] = -color[cur];
                        queue.add(node);
                    }
                    else {
                        if (color[node] != -color[cur]) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    /**
     * DFS solution
     */
    public boolean isBipartite_DFS(int[][] graph) {
        
        int[] colors = new int[graph.length];
        
        for (int i=0; i<graph.length; i++) {
            if (colors[i] == 0 && !validColor(colors, i, 1, graph)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean validColor(int[] colors, int cur, int color, int[][] graph) {
        if (colors[cur] != 0) {
            return colors[cur] == color;
        }     
        
        colors[cur] = color;
        
        for (int node : graph[cur]) {
            if (!validColor(colors, node, -color, graph)) {
                return false;
            }
        }
        
        return true;
    }
}
