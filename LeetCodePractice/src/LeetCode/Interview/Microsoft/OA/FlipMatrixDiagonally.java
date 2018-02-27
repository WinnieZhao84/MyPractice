package LeetCode.Interview.Microsoft.OA;

import java.util.Arrays;

/**
 * Created by WinnieZhao on 2/27/2018.
 */
public class FlipMatrixDiagonally {

    /**
     * flips by its main diagonal (top left -> bottom right): a[i][j] = a[j][i]
     * flips by the reverse diagonal (top right -> bottom left): a[i][j] = a[(size-1)-j][(size-1)-i]
     */


    public int[][] flipMainDiagonal(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] res = new int[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                res[j][i] = matrix[i][j];
            }
        }

        return res;
    }

    public int[][] flipReverseDiagonal(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int n = row-1;
        int[][] res = new int[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                res[j][i] = matrix[n-i][n-j];
            }
        }

        return res;
    }

    public int[][] flipBothDiagonal(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int n = row-1;

        int[][] res = new int[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (i != j && i+j != n) {
                    res[j][i] = matrix[n-i][n-j];
                }
                else {
                    res[i][j] = matrix[i][j];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        FlipMatrixDiagonally solution = new FlipMatrixDiagonally();

        int[][] res1 = solution.flipMainDiagonal(matrix);
        int[][] res2 = solution.flipReverseDiagonal(matrix);
        int[][] res3 = solution.flipBothDiagonal(matrix);

        Arrays.stream(res1).forEach(i -> System.out.println(Arrays.toString(i)));
        Arrays.stream(res2).forEach(i -> System.out.println(Arrays.toString(i)));
        Arrays.stream(res3).forEach(i -> System.out.println(Arrays.toString(i)));
    }
}
