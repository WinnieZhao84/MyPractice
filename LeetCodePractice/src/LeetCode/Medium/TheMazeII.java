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

    static final int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    class Pair {
        int x;
        int y;
        int len;
        public Pair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int m = maze.length;
        int n = maze[0].length;
        int[][] dp = new int[m][n];
        Queue<Pair> que = new LinkedList<>();

        que.offer(new Pair(start[0], start[1], 0));
        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }


        while(!que.isEmpty()) {
            Pair cur = que.poll();
            for(int[] dir : dirs) {
                int nextX = cur.x;
                int nextY = cur.y;
                int len = cur.len;

                while(nextX < m && nextX >= 0 && nextY < n && nextY >= 0 && maze[nextX][nextY] == 0) {
                    nextX += dir[0];
                    nextY += dir[1];
                    len++;

                }
                nextX -= dir[0];
                nextY -= dir[1];
                len--;

                // avoid going through unneccessary cases.
                if(len > dp[destination[0]][destination[1]]) {
                    continue;
                }

                if(len < dp[nextX][nextY]) {
                    dp[nextX][nextY] = len;
                    que.offer(new Pair(nextX, nextY, len));
                }
            }
        }

        return dp[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dp[destination[0]][destination[1]];
    }
}
