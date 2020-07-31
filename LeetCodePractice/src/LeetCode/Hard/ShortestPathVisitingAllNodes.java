package LeetCode.Hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 847. Shortest Path Visiting All Nodes
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 * 
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 * 
 * Example 1:
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * 
 * Example 2:
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 * 
 * Note:
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 * @author WinnieZhao
 *
 */
public class ShortestPathVisitingAllNodes {
    class Node {
        int id;
        int mask;
        
        Node(int id, int mask) {
            this.id=id;
            this.mask=mask;
        }
        
        public String toString() {
            return id + " " + mask;
        }
    }
    
    /**
     * How to represent a unique state
     * 
     * Node (cur_node, visited_node)
     * 
     * Use 32 bits int to represent visited_node
     * 
     * N=4
     * Node 0, 2, 3 are visited: 1101
     * Goal: 1111, 1<<4-1
     * 
     * Time complexity: O(n*2^n)
     * Space complexity: O(n*2^n)
     */
    public int shortestPathLength(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return -1;
        }
        
        int fullMask = (1 << graph.length) - 1;
        
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (int i=0; i<graph.length; i++) {
            Node node = new Node(i, 1<<i);
            queue.add(node);
            visited.add(node.toString());
        }
        
        int steps = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                Node cur = queue.poll();
                
                if (cur.mask == fullMask) {
                    return steps;
                }
                
                for (int next : graph[cur.id]) {
                    Node nextNode = new Node(next, cur.mask | (1 << next));
                    if (visited.add(nextNode.toString())) {
                        queue.add(nextNode);
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }

}
