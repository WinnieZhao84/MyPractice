package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * 
 * click to show follow up.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?

 * @author WinnieZhao
 *
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRow = false;
        boolean firstCol = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;

                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                }
            }
        }

        for (int i=1; i<m; i++) {
            if (matrix[i][0] == 0) {
                for (int j=1; j<n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j=1; j<n; j++) {
            if (matrix[0][j] == 0) {
                for (int i=1; i<m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRow) {
            for (int j=0; j<n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstCol) {
            for (int i=0; i<m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
    public static void main(String[] args) {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {{1,0,1},{1,1,1},{1,1,1}};
        solution.setZeroes(matrix);
        
        for (int[] nums : matrix) {
            System.out.println(Arrays.toString(nums));
        }
        
    }
}
