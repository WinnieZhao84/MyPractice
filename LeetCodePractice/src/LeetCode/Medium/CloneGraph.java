package LeetCode.Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import LeetCode.Helper.Node;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * OJ's undirected graph serialization:
 * 
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * 
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         
 * @author WinnieZhao
 *
 */
public class CloneGraph {

    Map<Node, Node> cache = new HashMap<>();
    
    // DFS
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        
        if (cache.get(node) != null) {
            return cache.get(node);
        }
        
        Node newNode = new Node(node.val);
        cache.put(node, newNode);
        
        if (node.neighbors == null || node.neighbors.isEmpty()) {
            return newNode;
        }
        
        for (Node n : node.neighbors) {
           newNode.neighbors.add(this.cloneGraph(n)); 
        }
        
        return newNode;
    }
   

    public Node cloneGraph_BFS(Node node) {
        if (node == null) {
            return node;
        }
        
        Map<Node, Node> cache = new HashMap<>();
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        Node newNode = new Node(node.val);
        cache.put(node, newNode);
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (Node neighbor : cur.neighbors) {
                if (!cache.containsKey(neighbor)) {
                    cache.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                cache.get(cur).neighbors.add(cache.get(neighbor));
                
            }
        }
        return newNode;
    }
}
