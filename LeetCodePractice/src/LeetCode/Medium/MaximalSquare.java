package LeetCode.Medium;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0
    Return 4.

 * @author WinnieZhao
 *
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;
        
        int[][] dp = new int[row][col];
        
        for (int i=0; i<col; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(dp[0][i], max);
        }
        
        for (int i=0; i<row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(dp[i][0], max);
        }
        

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max * max;
    }
    
    public static void main(String[] args) {
        MaximalSquare solution = new MaximalSquare();
        char[][] matrix = {{'1'}};
        System.out.println(solution.maximalSquare(matrix));
    }
}
