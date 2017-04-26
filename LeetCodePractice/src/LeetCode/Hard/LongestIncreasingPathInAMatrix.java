package LeetCode.Hard;

/**
 *
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * nums = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 *
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * nums = [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 *
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

 * Created by WinnieZhao on 4/26/2017.
 */
public class LongestIncreasingPathInAMatrix {

    // it is simply a dfs traversal on each node, while traversing, remember the longest distance from each node.

    int[][] neighbor = {{-1,0}, {0, -1}, {1, 0}, {0,1}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int[][] dis = new int[matrix.length][matrix[0].length];
        int res = 0;

        for(int i=0; i< matrix.length; i++) {
            for(int j=0; j< matrix[0].length; j++){
                res = Math.max(res, dfs(matrix, i, j, dis));
            }
        }

        return res;
    }

    private int dfs(int[][] matrix, int x, int y, int[][] dis){
        if(dis[x][y] != 0) return dis[x][y];

        for (int i=0; i<4; i++) {
            int k = x + neighbor[i][0];
            int l = y + neighbor[i][1];

            if(k >=0 && k<matrix.length && l >=0 && l < matrix[0].length && matrix[x][y] < matrix[k][l]){
                dis[x][y] = Math.max(dis[x][y], dfs(matrix, k, l, dis));
            }
        }

        return ++dis[x][y];
    }
}