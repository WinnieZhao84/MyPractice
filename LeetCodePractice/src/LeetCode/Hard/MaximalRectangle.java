package LeetCode.Hard;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 6.

 * Created by WinnieZhao on 2017/5/23.
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {

        /**
         * The DP solution proceeds row by row, starting from the first row.
         * Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j).
         *
         * All the 3 variables left, right, and height can be determined by the information from previous row and also
         * information from the current row.
         * So it can be regarded as a DP solution. The transition equations are:
         *
         * height means from top to this position, there are how many '1'
         * left means at current position, what is the index of left bound of the rectangle with height[j].
         * 0 means at this position, no rectangle.
         * right means the right bound index of this rectangle. 'n' means no rectangle.

         * left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
         * right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
         * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
         * height(i,j) = 0, if matrix[i][j]=='0'
         *
         */

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        int res = 0;

        Arrays.fill(right, n);
        Arrays.fill(height, 0);


        for (int i=0; i<m; i++) {

            for (int j=0; j<n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                }
                else {
                    height[j] = 0;
                }
            }

            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    cur_left = j + 1;
                    left[j] = 0;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    cur_right = j;
                    right[j] = n;
                }
            }
            for (int j = 0; j < n; j++) {
                res = Math.max(res, (right[j] - left[j])*height[j]);
            }
        }

        return res;

    }

    /**
     * Use the same logic as Largest Rectangle In Histogram
     *
     */
    class Solution {

        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }

            int res = 0;
            int row = matrix.length;
            int col = matrix[0].length;

            int[] heights = new int[col];

            // For the first line, get the height for each col
            for (int i=0; i<col; i++) {
                if (matrix[0][i] == '1') {
                    heights[i]++;
                }
            }

            res = this.getMaxRectangle(heights);

            for (int i=1; i<row; i++) {
                this.getNewHeights(heights, matrix, i);

                res = Math.max(res, this.getMaxRectangle(heights));
            }

            return res;
        }

        // For each new row, reset the heights array
        private void getNewHeights(int[] heights, char[][] matrix, int row) {
            for (int i=0; i<heights.length; i++) {
                if (matrix[row][i] == '1') {
                    heights[i] = heights[i] + 1;
                }
                else {
                    heights[i] = 0;
                }
            }
        }

        // Same logic as Largest Rectangle in Histogram
        private int getMaxRectangle(int[] heights) {
            int i=0;
            Stack<Integer> stack = new Stack<>();

            int max = 0;
            while (i <= heights.length) {
                int height = i < heights.length ? heights[i] : 0;

                if (stack.isEmpty() || height >= heights[stack.peek()] ) {
                    stack.push(i);
                    i++;
                }
                else {
                    int h = heights[stack.pop()];
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;

                    max = Math.max(max, h*w);
                }
            }

            return max;
        }
    }
}
