package LeetCode.Interview.Facebook;

/**
 *
 * 286
 *
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
 * Created by WinnieZhao on 2/4/2018.
 */
public class WallsAndGates {

    int m;
    int n;
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        m = rooms.length;
        n = rooms[0].length;

        for(int i=0; i< m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    this.dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs (int[][] rooms, int i, int j, int dist) {
        if (i<0 || j<0 || i>=m || j>=n || rooms[i][j] == -1) {
            return;
        }

        if( dist > rooms[i][j]) return;
        // don't really need the min methods, if it is 0 ,then only show once.
        rooms[i][j] = Math.min(rooms[i][j], dist);// since a room is already 0,
        // in first level, there is no set really.
        dfs(rooms, i+1, j, dist+1);
        dfs(rooms, i-1, j, dist+1);
        dfs(rooms, i, j+1, dist+1);
        dfs(rooms, i, j-1, dist+1);
    }
}
