package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" 
 * touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * 
 * Example:
 * 
 * 
 * Given the following 5x5 matrix:
 * 
 * Pacific ~   ~   ~   ~   ~ 
 *      ~  1   2   2   3  (5) *
 *      ~  3   2   3  (4) (4) *
 *      ~  2   4  (5)  3   1  *
 *      ~ (6) (7)  1   4   5  *
 *      ~ (5)  1   1   2   4  *
 *         *   *   *   *   * Atlantic
 *         
 * Return:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 * 
 * @author WinnieZhao
 *
 */
public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[][] atlantic = new boolean[m][n];
        boolean[][] pacific = new boolean[m][n];

        for (int i=0; i<m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, n-1);
        }
        
        for (int i=0; i<n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, m-1, i);
        }
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                List<Integer> list = new ArrayList<>();
                if (pacific[i][j] && atlantic[i][j]) {
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }
    
    private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
        
        if (x < 0 || y < 0 || x>=matrix.length || y >=matrix[0].length || visited[x][y] || matrix[x][y] < height) {
            return;
        }
        
        visited[x][y] = true;
        
        dfs(matrix, visited, matrix[x][y], x-1, y);
        dfs(matrix, visited, matrix[x][y], x+1, y);
        dfs(matrix, visited, matrix[x][y], x, y-1);
        dfs(matrix, visited, matrix[x][y], x, y+1);
    }
    
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    /**
     * BFS
     */
    public List<List<Integer>> pacificAtlantic_BFS(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[][] atlantic = new boolean[m][n];
        boolean[][] pacific = new boolean[m][n];
        
        Queue<int[]> aQueue = new LinkedList<>();
        Queue<int[]> pQueue = new LinkedList<>();

        for (int i=0; i<m; i++) {
            pacific[i][0] = true;
            atlantic[i][n-1] = true;
            
            pQueue.add(new int[]{i, 0});
            aQueue.add(new int[]{i, n-1});
        }
        
        for (int i=0; i<n; i++) {
            pacific[0][i] = true;
            atlantic[m-1][i] = true;
            
            pQueue.add(new int[]{0, i});
            aQueue.add(new int[]{m-1, i});
        }
        
        bfs(matrix, pacific, pQueue);
        bfs(matrix, atlantic, aQueue);
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                List<Integer> list = new ArrayList<>();
                if (pacific[i][j] && atlantic[i][j]) {
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }
    
    private void bfs(int[][] matrix, boolean[][] visited, Queue<int[]> queue) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            int height = matrix[i][j];
            
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                
                if (x < 0 || y< 0 || x>=m || y>=n || visited[x][y] || height > matrix[x][y]) {
                    continue;
                }
                
                visited[x][y] = true;
                queue.add(new int[] {x, y});
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();
        System.out.println(solution.pacificAtlantic(matrix));
    }
}
