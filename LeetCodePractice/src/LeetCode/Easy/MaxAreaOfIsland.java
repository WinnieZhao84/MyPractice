package LeetCode.Easy;

/**
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 *  Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 *
 *  Example 2:
 *  [[0,0,0,0,0,0,0,0]]
 *  Given the above grid, return 0.

 * Created by WinnieZhao on 10/21/2017.
 */
public class MaxAreaOfIsland {

    int count = 0;

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];
        int max = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (grid[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                count = 0;
                dfs(grid, visited, i, j, row, col);

                max = Math.max(count, max);
            }
        }

        return max;
    }

    private void dfs(int[][] grid, boolean[][] visited, int x, int y, int row, int col) {
        if (x<0 || y<0 || x>=row || y>=col || visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;

        if (grid[x][y] == 1) {
            count++;
        }

        dfs(grid, visited, x+1, y, row, col);
        dfs(grid, visited, x-1, y, row, col);
        dfs(grid, visited, x, y+1, row, col);
        dfs(grid, visited, x, y-1, row, col);
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();

        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};

        System.out.println(solution.maxAreaOfIsland(grid));
    }
}
