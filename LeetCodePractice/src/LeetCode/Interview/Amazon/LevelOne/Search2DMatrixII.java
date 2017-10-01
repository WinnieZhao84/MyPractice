package LeetCode.Interview.Amazon.LevelOne;

/**
 * Same as LeetCode
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = 0;
        int col = matrix[0].length-1;

        while (row < matrix.length && col >=0) {

            if (matrix[row][col] == target) {
                return true;
            }
            else if (matrix[row][col] > target) {
                col--;
            }
            else {
                row++;
            }
        }

        return false;
    }
}
