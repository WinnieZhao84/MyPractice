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

    private static int[][] dirs = {{-1,0}, {0, -1}, {1, 0}, {0,1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] memo = new int[row][col];

        int res = 1;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                res = Math.max(res, this.dfs(matrix, i, j, row, col, memo));
            }
        }

        return res;
    }

    private int dfs(int[][] matrix, int x, int y, int row, int col, int[][] memo) {

        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        int max = 1;

        for (int[] dir : dirs) {
            int m = x + dir[0];
            int n = y + dir[1];

            if (m >=0 && m < row && n >= 0 && n < col && matrix[x][y] < matrix[m][n]) {
                int len = 1 + dfs(matrix, m, n, row, col, memo);
                max = Math.max(max, len);

            }
        }

        memo[x][y] = max;

        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix solution = new LongestIncreasingPathInAMatrix();

        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};

        System.out.println(solution.longestIncreasingPath(matrix));
    }
}
