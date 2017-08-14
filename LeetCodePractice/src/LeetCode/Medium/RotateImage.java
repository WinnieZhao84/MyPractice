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

    /* Here give a common method to solve the image rotation problems:

     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     *
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
     */
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
