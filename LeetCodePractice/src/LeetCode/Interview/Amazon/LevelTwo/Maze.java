package LeetCode.Interview.Amazon.LevelTwo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给个array,其中只有一格是9，其他格子是0或1，0表示此路不通，1表示可以走，判断从（0,0) 点开始上下左右移动能否找到这个是9的格子
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class Maze {

    static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int checkMaze(int[][] maze) {
        if (maze == null || maze.length == 0 || maze[0][0] == 0) {
            return 0;
        }

        if(maze[0][0] == 9) {
            return 1;
        }
        int m = maze.length, n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        maze[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            for(int k = 0; k < 4; k++) {
                int x = p[0] + dir[k][0];
                int y = p[1] + dir[k][1];
                if(x >=0 && x < m && y >= 0&& y < n) {
                    if(maze[x][y] == 9) {
                        return 1;
                    }
                    else if(maze[x][y] == 1) {
                        queue.offer(new int[]{x, y});
                        maze[x][y] = 0;
                    }
                }
            }
        }

        return 0;
    }
}
