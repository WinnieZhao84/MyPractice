package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934. Shortest Bridge
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * 
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * 
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 * Example 1:
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 * 
 * Example 2:
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * Example 3:
 * Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * Constraints:
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1

 * @author WinnieZhao
 *
 */
public class ShortestBridge {

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    /**
     * Time Complexity: m*n
     * Space Complexity: m*n
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i=0; i<row && queue.isEmpty(); i++) {
            for (int j=0; j<col && queue.isEmpty(); j++) {
                if (A[i][j] == 1) {
                    dfs(i, j, queue, A);
                }
            }
        }
        
        
        int distance=0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                int[] cur = queue.poll();
                
                for (int[] dir : dirs) {
                    int x=cur[0] + dir[0];
                    int y=cur[1] + dir[1];
                
                    
                    if (x<0 || y<0 || x>=row || y>=col || A[x][y] == -1) {
                        continue;
                    }
                                        
                    if (A[x][y] == 1) {
                        return distance;
                    }
                    
                    A[x][y]=-1;
                    queue.add(new int[] {x, y});
                }
            }
            distance++;
        }
        return -1;
    }
    
    
    private void dfs(int x, int y, Queue<int[]> queue, int[][] A) {
        if (x < 0 || y < 0 || x >= A.length || y >= A[0].length || A[x][y] != 1) {
            return;
        }
        A[x][y] = -1; // mark the visited island to -1
        
        queue.add(new int[]{x, y});
        
        dfs(x-1, y, queue, A);
        dfs(x+1, y, queue, A);
        dfs(x, y-1, queue, A);
        dfs(x, y+1, queue, A);
        
    }
}
