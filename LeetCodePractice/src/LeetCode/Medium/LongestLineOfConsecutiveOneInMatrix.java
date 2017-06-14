package LeetCode.Medium;

/**
 * 562
 *
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 *
 * Example:
 *
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 *
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.

 * Created by WinnieZhao on 2017/6/13.
 */
public class LongestLineOfConsecutiveOneInMatrix {

    /**
     * Java straight forward solution
     *
     * @param M
     * @return
     */
    public int longestLine(int[][] M) {
        if(M == null) return 0;
        int res = 0;
        for(int i =0;i<M.length;i++){
            for(int j = 0;j<M[0].length;j++){
                if(M[i][j] == 1){
                    res = Math.max(res,getMaxOneLine(M, i, j));
                }
            }
        }
        return res;
    }

    final int [][] dirs = new int[][]{{1,0},{0,1},{1,1},{1,-1}};

    private int getMaxOneLine(int [][] M, int x, int y){
        int res = 1;
        for(int [] dir:dirs){
            int i = x+dir[0];
            int j = y+dir[1];
            int count = 1;
            while(isValidPosition(M, i, j) && M[i][j] == 1){
                i+=dir[0];
                j+=dir[1];
                count++;
            }
            res = Math.max(count,res);
        }
        return res;
    }

    private boolean isValidPosition(int M[][], int i, int j){
        return (i<M.length && j<M[0].length && i>=0 && j>=0);
    }

    /**
     * DP solution
     * @param M
     * @return
     */
    public int longestLine_dp(int[][] M) {
        int max = 0;
        int n = M.length;

        if (n == 0) return max;

        int m = M[0].length;
        int[][][] dp = new int[n][m][4];

        /**
         * h[x][y] = M[x][y] * (h[x - 1][y]  + 1)
         *
         * v[x][y] = M[x][y] * (v[x][y - 1]  + 1)
         *
         * d[x][y] = M[x][y] * (d[x - 1][y - 1]  + 1)
         *
         * a[x][y] = M[x][y] * (a[x + 1][y - 1]  + 1)
         */

        for (int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if (M[i][j] == 0) continue;

                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1;
                }

                if (j > 0 && M[i][j - 1] == 1) {
                    dp[i][j][0] += dp[i][j - 1][0]; // horizontal line
                }
                if (j > 0 && i > 0 && M[i - 1][j - 1] == 1) {
                    dp[i][j][1] += dp[i - 1][j - 1][1]; // diagonal line
                }
                if (i > 0 && M[i - 1][j] == 1) {
                    dp[i][j][2] += dp[i - 1][j][2]; // vertical line
                }
                if (j < m - 1 && i > 0 && M[i - 1][j + 1] == 1) {
                    dp[i][j][3] += dp[i - 1][j + 1][3]; // anti-diagonal line
                }

                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            }
        }
        return max;
    }
}
