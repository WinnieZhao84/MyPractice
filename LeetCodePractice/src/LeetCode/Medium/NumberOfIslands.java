package LeetCode.Medium;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Answer: 1
 * 
 * Example 2:
 * 
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Answer: 3
 *
 * @author WinnieZhao
 *
 */
public class NumberOfIslands {

   class Island {
        int x;
        int y;
        
        Island (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // BFS
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
       
        int num = 0;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {

                if (grid[i][j] == '1') {
                    Queue<Island> queue = new LinkedList<>();        
                    
                    Island island = new Island(i, j);
                    queue.add(island);
                    visited[i][j] = true;
                    
                    while (!queue.isEmpty()) {
                        Island cur = queue.poll();
                        
                        for (int k=0; k<4; k++) {
                            int x = cur.x + directions[k][0];
                            int y = cur.y + directions[k][1];
                            
                            if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == '1' && !visited[x][y]) {
                                grid[x][y] = '0';
                                visited[x][y] = true;
                                queue.offer(new Island(x, y));
                            }
                        }
                    }
                    num++;
                }

            }
        }
        return num;
    }

    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        int num = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j, m, n, visited);
                }
            }
        }
        return num;
    }
    
    private void dfs(char[][] grid, int x, int y, int m, int n, boolean[][] visited) {
        if (x < 0 || y < 0 || x >=m || y >= n || visited[x][y] || grid[x][y] == '0') {
            return;
        }
        
        grid[x][y] = '0';
        visited[x][y] = true;
        
        dfs(grid, x-1, y, m, n, visited);
        dfs(grid, x+1, y, m, n, visited);
        dfs(grid, x, y-1, m, n, visited);
        dfs(grid, x, y+1, m, n, visited);
        
    }
}
