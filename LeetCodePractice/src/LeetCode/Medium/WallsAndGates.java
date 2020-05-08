package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room.
 * We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume
 * that the distance to a gate is less than 2147483647. Fill each empty room
 * with the distance to its nearest gate. If it is impossible to reach a gate,
 * it should be filled with INF.
 *
 * For example, given the 2D grid:
 * INF  -1   0     INF
 * INF  INF  INF   -1
 * INF  -1   INF   -1
 * 0    -1   INF   INF
 *
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 *
 * Created by WinnieZhao on 2017/4/5.
 */
public class WallsAndGates {

   public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    this.dfs(rooms, i, j, m, n, 0);
                }
            }
        }
    }
    
    private void dfs(int[][] rooms, int x, int y, int m, int n, int distance) {
        if (x<0 || y<0 || x>=m || y>=n || rooms[x][y]<distance) {
            return;
        }
        
        rooms[x][y] = distance;
        
        dfs(rooms, x-1, y, m, n, distance+1);
        dfs(rooms, x+1, y, m, n, distance+1);
        dfs(rooms, x, y-1, m, n, distance+1);
        dfs(rooms, x, y+1, m, n, distance+1);
    }

    class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates_BFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        
        Queue<Point> queue = new LinkedList<>();
        
        for (int i=0; i<rooms.length; i++) {
            for (int j=0; j<rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                  queue.add(new Point(i,j));
                }
            }
        }
        
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        int m = rooms.length;
        int n = rooms[0].length;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                Point cur = queue.poll();
                
                for (int[] dir : dirs) {
                    int x = cur.x + dir[0];
                    int y = cur.y + dir[1];
                    
                    if (x >= m || x < 0 || y <0 || y >=n || rooms[x][y] < rooms[cur.x][cur.y]+1) {
                        continue;
                    }
                    
                    rooms[x][y] = rooms[cur.x][cur.y] + 1;
                    queue.add(new Point(x,y));
                }
            }

        }
    }
}
