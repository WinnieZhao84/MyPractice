package LeetCode.Medium;

/**
 * 490
 *
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders
 * of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 (0)
 * 0 0 0 0  0
 * 0 0 0 1  0
 * 1 1 0 1  1
 * 0 0 0 0 [0]
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 0  0  1  0 (0)
 * 0  0  0  0  0
 * 0  0  0  1  0
 * 1  1 [0] 1  1
 * 0  0  0  0  0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 *
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume
 * the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 * Created by WinnieZhao on 2017/4/6.
 */
public class TheMaze {

    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
    class Cell {
        int x;
        int y;
        
        Cell (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
       
        if (maze == null || maze.length == 0) {
            return false;
        }

        int m = maze.length;
        int n = maze[0].length;
    
        boolean[][] visited = new boolean[m][n];
        return dfs(maze, visited, start, destination);
    }
    
    private boolean dfs(int[][] maze, boolean[][] visited, int[] cur, int[] destination) {
        int x = cur[0];
        int y = cur[1];
        
        if (visited[x][y]) {
            return false;
        }
        if (x == destination[0] && y == destination[1]) {
            return true;
        }
        
        visited[x][y] = true;
        
        for (int[] dir : dirs) {
            x = cur[0];
            y = cur[1];
            
            while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            if (dfs(maze, visited, new int[] {x-dir[0], y-dir[1]}, destination)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasPath_BFS(int[][] maze, int[] start, int[] destination) {
       
       if (maze == null || maze.length == 0) {
           return false;
       }
       
       Queue<Cell> queue = new LinkedList<>();
       queue.add(new Cell(start[0], start[1]));
       
       int m = maze.length;
       int n = maze[0].length;
    
       boolean[][] visited = new boolean[m][n];
              
       while(!queue.isEmpty()) {
            Cell cur = queue.poll();
   
            if (cur.x == destination[0] && cur.y == destination[1]) {
                return true;
            }
            
            for (int[] dir : dirs) {
                int x = cur.x;
                int y = cur.y;
                
                while (x>=0 && y>=0 && x<m && y< n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
               
                int lastX = x-dir[0];
                int lastY = y-dir[1];
                if (!visited[lastX][lastY]) {
                    visited[lastX][lastY] = true;
                    queue.add(new Cell(lastX, lastY));
                }
            }
        }
        return false;
    }
}
