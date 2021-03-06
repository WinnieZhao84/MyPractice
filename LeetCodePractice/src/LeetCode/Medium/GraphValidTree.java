package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 261
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * This is an undirected map, so in-degree methods is not very suitable. Using bfs, since in a valid tree traversal,
 * each node should be only visited once, if there is a loop, you end up visit an already visited node, this is not
 * a valid tree. using a queue, every time, if node is not visited, mark it as visited, then traverse its neighbors,
 * if is not visited, enque it, if there is any cycle in the graph, your queue ends up containing multiple instance
 * of certain numbers.

 * Created by WinnieZhao on 2017/3/28.
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            Integer num = queue.poll();

            if (visited[num]) {
                return false;
            }

            visited[num] = true;

            for (Integer neighbor : list.get(num)) {
                if (!visited[neighbor]){
                    queue.offer(neighbor);
                }
            }
        }

        for(boolean b : visited){
            if(!b) return false;
        }
        return true;
    }
    
    public boolean validTree_DFS(int n, int[][] edges) {
       if (n==1) {
           return true;
       }
              
       if (n==0 || edges.length ==0) {
           return false;
       }
       
       List<List<Integer>> connections = new ArrayList<>();
       for (int i=0; i<n; i++) {
           connections.add(new ArrayList<>());
       }
       
       for (int i=0; i<edges.length; i++) {
            connections.get(edges[i][0]).add(edges[i][1]);
            connections.get(edges[i][1]).add(edges[i][0]);
       }

       boolean[] visited = new boolean[n];
       
       dfs(connections, visited, 0, -1);
       
       for (boolean b : visited) {
           if (!b) {
               return false;
           }
       }
       
       return true;
    }
    
    private boolean dfs(List<List<Integer>> connections, boolean[] visited, int cur, int parent) {
        if (visited[cur]) {
            return false;
        }
        
        visited[cur] = true;
        for (Integer next : connections.get(cur)) {
            // Check if the next makes a cycle when next is not cur's parent (e.g. 0->1, 1->0)
            if (next != parent && !dfs(connections, visited, next, cur)) {
                return false;
            }
        }
        return true;
    }
}
