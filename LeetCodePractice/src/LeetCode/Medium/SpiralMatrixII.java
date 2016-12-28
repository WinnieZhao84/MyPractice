package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * 
 * For example,
 * Given n = 3, You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

 * @author WinnieZhao
 *
 */
public class SpiralMatrixII {
    
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        if (n == 0) return matrix;
        
        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int columnStart = 0;
        int columnEnd = matrix[0].length-1;
        int count = 1;
        
        while(rowStart<=rowEnd && columnStart<=columnEnd) {
            // Traverse right
            for (int i= columnStart; i<=columnEnd; i++) {
                matrix[rowStart][i] = count++;
            }
            rowStart++;
            
            // Traverse down
            for (int i=rowStart; i<=rowEnd; i++) {
                matrix[i][columnEnd] = count++;
            }
            columnEnd--;
            
            // Traverse left
            if (rowStart <= rowEnd) {
                for (int i=columnEnd; i>=columnStart; i--) {
                    matrix[rowEnd][i] = count++;
                }
            }
            rowEnd--;
            
            // Traverse up
            if (columnStart <= columnEnd) {
                for (int i=rowEnd; i>=rowStart; i--) {
                    matrix[i][columnStart] = count++;
                }
            }
            columnStart++;
        }
        
        return matrix;
    }
    
    public static void main(String[] args) {
        SpiralMatrixII solution = new SpiralMatrixII();
        int[][] matrix = solution.generateMatrix(0);
        
        for (int[] nums : matrix) {
            System.out.println(Arrays.toString(nums));
        }
        
    }
}
