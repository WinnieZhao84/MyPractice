package LeetCode.Medium;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?

 * @author WinnieZhao
 *
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int row = matrix.length;
        int column = matrix[0].length;
        
        this.reverse(matrix);
        for (int i=0; i<row; i++) {
            for (int j=i+1; j<column; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    
    private void reverse(int[][] matrix) {
        int start = 0;
        int row = matrix.length-1;
        
        while (start < row) {
            int[] array = matrix[row];
            
            matrix[row] = matrix[start];
            matrix[start] = array;
            
            row--;
            start++;
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        
        RotateImage solution = new RotateImage();
        solution.rotate(matrix);
        
        for (int[] nums : matrix) {
            System.out.println(Arrays.toString(nums));
        }
        
    }
}
