package LeetCode.Hard;

import java.util.Arrays;

import static LeetCode.Hard.StudentAttendanceRecordII.M;

/**
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball,
 * you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
 * However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 10^9 + 7.
 *
 * Example 1:
 * Input:m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 *
 *
 * Example 2:
 * Input:m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * Explanation:
 *
 * Note:
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50]. N is in range [0,50].

 * Created by WinnieZhao on 5/23/2017.
 */
public class OutOfBoundaryPaths {

    // Time Limit Exceeded
    // Time complexity : O(4^n) .Space complexity : O(n)
    public int findPaths(int m, int n, int N, int i, int j) {
        if(i==m || j==n || i<0 ||j<0)
            return 1;
        if(N==0)
            return 0;
        return findPaths(m,n,N-1,i-1,j) + findPaths(m,n,N-1,i+1,j) + findPaths(m,n,N-1,i,j-1) + findPaths(m,n,N-1,i,j+1);
    }

    // Recursion with memoization
    // Thus, a lot of redundant function calls are made with the same set of parameters leading to a useless increase in runtime.
    // We can remove this redundancy by making use of a memoization array, memo. memo[i][j][k] is used to store
    // the number of possible moves leading to a path out of the boundary if the current position is given by the indices (i, j)
    // and number of moves left is k.
    public int findPaths_better(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N+1];
        for(int[][] l:memo) {
            for(int[] sl:l) {
                Arrays.fill(sl, -1);
            }
        }
        return findPaths(m,n,N,i,j,memo);
    }

    private final static int M=1000000007;

    private int findPaths(int m, int n, int N, int i, int j,int[][][] memo) {
        if(i==m || j==n || i<0 ||j<0)
            return 1;
        if (N==0)
            return 0;
        if(memo[i][j][N]>=0)
            return memo[i][j][N];

        memo[i][j][N] = ((findPaths(m,n,N-1,i-1,j,memo)+findPaths(m,n,N-1,i+1,j,memo)) % M + (findPaths(m,n,N-1,i,j-1,memo)+findPaths(m,n,N-1,i,j+1,memo)) %M) %M;
        return memo[i][j][N];
    }
}
