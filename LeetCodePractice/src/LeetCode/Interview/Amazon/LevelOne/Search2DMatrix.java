package LeetCode.Interview.Amazon.LevelOne;

/**
 * Same as LeetCode
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class Search2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;

            int i = mid / col;
            int j = mid % col;

            if (matrix[i][j] == target) {
                return true;
            }
            else if (matrix[i][j] < target) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        return false;
    }
}
