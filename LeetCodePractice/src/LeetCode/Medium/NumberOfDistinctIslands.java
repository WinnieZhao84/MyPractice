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
     * Hash By Local Coordinates
     *
     * Since two islands are the same if one can be translated to match another,
     * let's translate every island so the top-left corner is (0, 0)
     * For example, if an island is made from squares [(2, 3), (2, 4), (3, 4)],
     * we can think of this shape as [(0, 0), (0, 1), (1, 1)] when anchored at the top-left corner.
     * From there, we only need to check how many unique shapes there ignoring permutations
     * (so [(0, 0), (0, 1)] is the same as [(0, 1), (1, 0)]). We use sets directly as we have showcased below,
     * but we could have also sorted each list and put those sorted lists in our set structure shapes.
     *
     */
    int[][] grid;
    boolean[][] seen;
    Set<Integer> shape;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set<Set<Integer>> shapes = new HashSet<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new HashSet<>();
                explore(r, c, r, c);

                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }

    public void explore(int r, int c, int r0, int c0) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;

            // we converted our tuple (r - r0, c - c0) to integers.
            // We multiplied the number of rows by 2 * grid[0].length instead of grid[0].length
            // because our local row-coordinate could be negative.
            shape.add((r - r0) * 2 * grid[0].length + (c - c0));

            explore(r+1, c, r0, c0);
            explore(r-1, c, r0, c0);
            explore(r, c+1, r0, c0);
            explore(r, c-1, r0, c0);
        }
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands solution = new NumberOfDistinctIslands();

        int[][] grids = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,1,1},{1,1,0,1,0}};

        System.out.println(solution.numDistinctIslands(grids));
    }

}
