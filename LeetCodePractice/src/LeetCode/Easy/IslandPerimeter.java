package LeetCode.Easy;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
 * and there is exactly one island (i.e., one or more connected land cells). 
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
 * Determine the perimeter of the island.
 * 
 * Example:
 * 
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *  
 *  Answer: 16
 *  Explanation: The perimeter is the 16 yellow stripes in the image below:
 *  
 * @author WinnieZhao
 *
 */
public class IslandPerimeter {

    /**
     * loop over the matrix and count the number of islands
     * if the current dot is an island, count if it has any right neighbour or down neighbour;
     * the result is islands * 4 - neighbours * 2
     * 
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null) return 0;
        
        int row = grid.length;
        int column = grid[0].length;
        
        int islands = 0, neighbours = 0;
        for (int i=0; i< row; i++) {
            for (int j=0; j<column; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    if (i+1 < row && grid[i+1][j] == 1) {
                        neighbours++;
                    }
                    if (j+1 < column && grid[i][j+1] == 1) {
                        neighbours++;
                    }
                }
            }
        }
        
        return islands*4 - neighbours*2;
    }
}
