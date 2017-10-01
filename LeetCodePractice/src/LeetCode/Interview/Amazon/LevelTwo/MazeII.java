package LeetCode.Interview.Amazon.LevelTwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the shortest path from point [0,0] to the exit.
 *
 * Created by WinnieZhao on 9/28/2017.
 */
public class MazeII {
    public static void main (String[] args){
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start = {0,4};
        int[] end = {4,4};
        System.out.println(shortestDistance(maze,start,end));
    }

    static final int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Pair {
        int x;
        int y;
        int len;
        public Pair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(start[0], start[1], 0));
        for(int i = 0; i < m; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
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
                if(len > distance[destination[0]][destination[1]]) {
                    continue;
                }

                if(len < distance[nextX][nextY]) {
                    distance[nextX][nextY] = len;
                    queue.offer(new Pair(nextX, nextY, len));
                }
            }
        }

        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
}
