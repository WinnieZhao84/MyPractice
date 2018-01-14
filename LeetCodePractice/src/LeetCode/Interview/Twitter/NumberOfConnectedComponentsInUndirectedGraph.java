package LeetCode.Interview.Twitter;

/**
 * 323
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Created by WinnieZhao on 2017/4/4.
 */
public class NumberOfConnectedComponentsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        if (n <=0 || edges.length == 0) {
            return 0;
        }

        int[] visited = new int[n];

        int count = 0;
        for (int i=0; i<n; i++) {
            this.dfs(i, edges, visited);
            if (visited[i] == 0) {
                count++;
            }
        }

        return count;

    }

    private void dfs(int num, int[][]edge, int[] visited) {
        int n = edge.length;
        for (int i=0; i<n; i++) {
            if (visited[num] == 0 && edge[i][0] == num) {
                visited[num] = 1;
                dfs(edge[i][1], edge, visited);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfConnectedComponentsInUndirectedGraph solution = new NumberOfConnectedComponentsInUndirectedGraph();

        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};

        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        System.out.println(solution.countComponents(5, edges1));
        System.out.println(solution.countComponents(5, edges2));
    }
}
