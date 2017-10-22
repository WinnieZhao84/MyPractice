package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 694
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid
 * are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and
 * only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 *
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 * 1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.

 * Created by WinnieZhao on 10/21/2017.
 */
public class NumberOfDistinctIslands {

    /**
     * When we start a depth-first search on the top-left square of some island,
     * the path taken by our depth-first search will be the same if and only if
     * the shape is the same. We can exploit this by recording the path we take
     * as our shape - keeping in mind to record both when we enter and when we
     * exit the function.
     *
     */
    int[][] grid;
    boolean[][] seen;
    ArrayList<Integer> shape;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set<ArrayList<Integer>> shapes = new HashSet<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new ArrayList<>();
                explore(r, c, 0);

                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }

    public void explore(int r, int c, int di) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(di);
            explore(r+1, c, 1);
            explore(r-1, c, 2);
            explore(r, c+1, 3);
            explore(r, c-1, 4);
            shape.add(0);
        }
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands solution = new NumberOfDistinctIslands();

        int[][] grids = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,1,1},{1,1,0,1,0}};

        System.out.println(solution.numDistinctIslands(grids));
    }

}
