package LeetCode.Medium;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below). 
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right 
 * corner of the grid (marked 'Finish' in the diagram below).
 * 
 * How many possible unique paths are there? 
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.

 * @author WinnieZhao
 *
 */
public class UniquePaths {
    
    public int uniquePaths(int m, int n) {
        if (m == n && m == 0) {
            return 0;
        }
        
        int[][] paths = new int[m+1][n+1];
        
        paths[0][0] = 0;
        paths[0][1] = 0;
        paths[1][0] = 0;
        paths[1][1] = 1;
        
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (i==j && i==1) continue;
                paths[i][j] = paths[i][j-1] + paths[i-1][j];
                System.out.println(i + "-" + j + ": " + paths[i][j]);
            }
        }
        
        return paths[m][n];
        
    }
    
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        
        System.out.println(solution.uniquePaths(4, 3));
    }
}
