package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 505
 *
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop
 * at the destination.
 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded)
 * to the destination (included).
 * If the ball cannot stop at the destination, return -1.
 *
 * Created by WinnieZhao on 4/10/2017.
 */
public class TheMazeII {

    class Cell {
        int x;
        int y;
        
        Cell (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    // Time complexity : O(m*n*max(m,n)). Complete traversal of maze will be done in the worst case. 
    // Here, mm and nn refers to the number of rows and columns of the maze. Further, for every current node chosen, 
    // we can travel upto a maximum depth of max(m,n) in any direction.
    // Space complexity : O(mn). distance array of size m*n is used.     
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return -1;
        }
        
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(start[0], start[1]));
       
        int m = maze.length;
        int n = maze[0].length;
        
        // two matrix minimum value, common approach is to keep a DP matrix as record
        int[][] distance = new int[m][n];
        for (int[] row: distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
            
        distance[start[0]][start[1]] = 0;    
        int step = 0;
        while(!queue.isEmpty()) {
                
            Cell cur = queue.poll();

            for (int[] dir : dirs) {
                int x = cur.x;
                int y = cur.y;
                
                step = 0;
                while (x >=0 && y>=0 && x<m && y< n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    step++;
                }
                
                int lastX = x - dir[0];
                int lastY = y - dir[1];
                step--;
                
                if (distance[lastX][lastY] > distance[cur.x][cur.y] + step) {
                    distance[lastX][lastY] = distance[cur.x][cur.y] + step;
                    queue.add(new Cell(lastX, lastY));
                }
            }
        }
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
  
    // Time complexity : O(m*n*max(m,n)). Complete traversal of maze will be done in the worst case. 
    // Here, mm and nn refers to the number of rows and columns of the maze. Further, for every current node chosen, 
    // we can travel upto a maximum depth of max(m,n) in any direction.
    // Space complexity : O(mn). distance array of size m*n is used. 
    public int shortestDistance_DFS(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return -1;
        }
        
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(start[0], start[1]));
       
        int m = maze.length;
        int n = maze[0].length;
        
        // two matrix minimum value, common approach is to keep a DP matrix as record
        int[][] distance = new int[m][n];
        for (int[] row: distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
            
        distance[start[0]][start[1]] = 0;
        
        dfs(maze, start, distance);
        
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
    
    private void dfs(int[][] maze, int[] cur, int[][] distance) {
        for (int[] dir : dirs) {
            int x = cur[0];
            int y = cur[1];
        
            int step = 0;
            while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y] == 0) {
                x+=dir[0];
                y+=dir[1];
                
                step++;
            }
                            
            int lastX = x - dir[0];
            int lastY = y - dir[1];
            step--;
                
            if (distance[lastX][lastY] > distance[cur[0]][cur[1]] + step) {
                distance[lastX][lastY] = distance[cur[0]][cur[1]] + step;
                dfs(maze, new int[]{lastX,lastY}, distance);
            }
        }
        return;
    }
}
