package LeetCode.Hard;

/**
 * 308
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * [
 *  [3,  0,  1,  4,  2],
 *  [5,  6,  3,  2,  1],
 *  [1, (2), 0,  1,  5],
 *  [4,  1,  0,  1,  7],
 *  [1,  0,  3 ,(0), 5]
 * ]
 *
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *  [3, 0, 1, 4, 2],
 *  [5, 6, 3, 2, 1],
 *  [1, 2, 0, 1, 5],
 *  [4, 1, 0, 1, 7],
 *  [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.

 * Created by WinnieZhao on 2017/7/13.
 */
public class RangeSumQuery2DMutable {

    // Your NumMatrix object will be instantiated and called as such:
    // NumMatrix numMatrix = new NumMatrix(matrix);
    // numMatrix.sumRegion(0, 1, 2, 3);
    // numMatrix.update(1, 1, 10);
    // numMatrix.sumRegion(1, 2, 3, 4);

    private int[][] colSums; // Use colSums[i][j] = the sum of ( matrix[0][j], matrix[1][j], matrix[2][j],......, matrix[i - 1][j] ).
    private int[][] matrix;

    public RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        this.matrix = matrix;

        int m   = matrix.length;
        int n   = matrix[0].length;
        colSums = new int[m + 1][n];

        for(int i = 1; i <= m; i++) {
            for(int j = 0; j < n; j++){
                colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
            }
        }
    }

    //time complexity for the worst case scenario: O(m)
    public void update(int row, int col, int val) {
        for(int i = row + 1; i < colSums.length; i++){
            colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
        }

        matrix[row][col] = val;
    }

    //time complexity for the worst case scenario: O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;

        for(int j = col1; j <= col2; j++){
            ret += colSums[row2 + 1][j] - colSums[row1][j];
        }

        return ret;
    }
}
