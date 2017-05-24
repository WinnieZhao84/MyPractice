package LeetCode.Hard;

/**
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 *
 * Example 1:
 * Input: [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * Output: 23
 *
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 *
 * Note: The number of boxes n would not exceed 100.

 * Created by WinnieZhao on 2017/5/23.
 */
public class RemoveBoxes {

    /**
     * Define as T(i, j, k) which denotes the maximum points possible by removing the boxes of sub array boxes[i, j] with k boxes
     * attached to its left of the same color as boxes[i]
     *
     * The recurrence relation is as follows and the maximum points will be the larger of the two cases:
     * a. If we remove boxes[i] first, we get (k + 1) * (k + 1) + T(i + 1, j, 0) points,
     *    where for the first term, instead of 1 we again get (k + 1) * (k + 1) points for removing boxes[i] due to the attached boxes to its left;
     *    and for the second term there will be no attached boxes so we have the 0 in this term.
     * b. If we decide to attach boxes[i] to some other box of the same color, say boxes[m], then from our analyses above,
     *    the total points will be T(i + 1, m - 1, 0) + T(m, j, k + 1), where for the first term, since there is no attached boxes for sub array boxes[i + 1, m - 1],
     *    we have k = 0 for this part;
     *    while for the second term, the total number of attached boxes for subarray boxes[m, j] will increase by 1 because apart from the original k boxes,
     *    we have to account for boxes[i] now, so we have k + 1 for this term.
     *    But we are not done yet. What if there are multiple boxes of the same color as boxes[i]? We have to try each of them and choose the one that yields
     *    the maximum points. Therefore the final answer for this case will be: max(T(i + 1, m - 1, 0) + T(m, j, k + 1)) where i < m <= j && boxes[i] == boxes[m].

     *
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, dp);
    }

    private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] dp) {
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];

        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, dp);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp));
            }
        }

        dp[i][j][k] = res;
        return res;
    }
}

