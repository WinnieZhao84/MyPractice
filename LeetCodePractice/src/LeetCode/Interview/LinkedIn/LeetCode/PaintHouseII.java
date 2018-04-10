package LeetCode.Interview.LinkedIn.LeetCode;

/**
 * 265
 *
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0;
 * costs[1][2]is the cost of painting house 1 with color 2, and so on...
 *
 * Find the minimum cost to paint all houses.
 *
 * Note: All costs are positive integers.
 *
 * Follow up: Could you solve it in O(nk) runtime?
 * Created by WinnieZhao on 4/25/2017.
 */
public class PaintHouseII {

    /**
     * Explanation: dp[i][j] represents the min paint cost from house 0 to house i when house i use color j;
     * The formula will be dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].
     *
     * Take a closer look at the formula, we don't need an array to represent dp[i][j], we only need to know
     * the min cost to the previous house of any color and if the color j is used on previous house to get
     * prev min cost, use the second min cost that are not using color j on the previous house.
     *
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if(costs != null && costs.length == 0) return 0;

        int prevMin = 0, prevSec = 0, prevIdx = -1;

        for(int i = 0; i < costs.length; i++){
            int currMin = Integer.MAX_VALUE, currSec = Integer.MAX_VALUE, currIdx = -1;

            for(int j = 0; j < costs[0].length; j++) {
                costs[i][j] = costs[i][j] + (prevIdx == j ? prevSec : prevMin);

                 // Find the first min and second min, for the first min keep the index
                if(costs[i][j] < currMin){
                    currSec = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                }
                else if (costs[i][j] < currSec){
                    currSec = costs[i][j];
                }
            }
            prevMin = currMin;
            prevSec = currSec;
            prevIdx = currIdx;
        }
        return prevMin;
    }

    public class Solution {
        public int minCostII(int[][] costs) {
            if (costs == null || costs.length == 0) {
                return 0;
            }

            int n = costs.length;
            int k = costs[0].length;

            // dp[i][j] means the min cost painting for house i, with color j
            int[][] dp = new int[n][k];

            // Initialization
            for (int i = 0; i < k; i++) {
                dp[0][i] = costs[0][i];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int m = 0; m < k; m++) {
                        if (m != j) {
                            dp[i][j] = Math.min(dp[i - 1][m] + costs[i][j], dp[i][j]);
                        }
                    }
                }
            }

            // Final state
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                minCost = Math.min(minCost, dp[n - 1][i]);
            }

            return minCost;
        }
    }

}
