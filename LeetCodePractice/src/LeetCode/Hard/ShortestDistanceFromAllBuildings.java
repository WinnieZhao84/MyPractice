package LeetCode.Hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 317
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 *
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 *
 * Note: There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

 * Created by WinnieZhao on 2017/7/13.
 */
public class ShortestDistanceFromAllBuildings {

    /**
     * Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to this building.
     * After we do this for all the buildings, we can get the sum of shortest distance from every '0' to all reachable buildings.
     * This value is stored in 'distance[][]'. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance
     * from this block to all reachable buildings.
     *
     * Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)
     * We also count how many building each '0' can be reached. It is stored in reach[][]. This can be done during the BFS.
     * We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.
     *
     * Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)
     *
     * The total time complexity will be O(m^2*n^2), which is quite high!. Please let me know if I did the analysis wrong
     * or you have better solution.

     * @param grid
     * @return
     */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] distance = new int[rows][cols];
        int[][] reach = new int[rows][cols];
        int buildingNum = 0;

        // Do BFS for each building
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    bfs(grid, i, j, distance, reach);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && distance[i][j] != 0 && reach[i][j] == buildingNum)
                    min = Math.min(min, distance[i][j]);
            }
        }

        if (min < Integer.MAX_VALUE)
            return min;
        return -1;
    }

    private void bfs(int[][] grid, int row, int col, int[][] distance, int[][] reach) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        boolean[][] visited = new boolean[rows][cols];
        int level = 0;

        while (!q.isEmpty()) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] coords = q.remove();

                for (int k = 0; k < dirs.length; k++) {
                    int x = coords[0] + dirs[k][0];
                    int y = coords[1] + dirs[k][1];

                    if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && grid[x][y] == 0) {
                        visited[x][y] = true;
                        distance[x][y] += level;
                        reach[x][y]++;

                        q.add(new int[]{x, y});
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        
        ShortestDistanceFromAllBuildings solution = new ShortestDistanceFromAllBuildings();
        System.out.println(solution.shortestDistance(grid));
        
    } 
}
