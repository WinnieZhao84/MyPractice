package LeetCode.Easy;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such
 * that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.

 Note: All costs are positive integers.

 * Created by WinnieZhao on 2017/3/24.
 */
public class PaintHouse {
    /**
     *         [
     * red:     [2, 3, 4, 4, 5 ,6]
     * blue     [4, 5, 6, 3, 2, 1]
     * green    [1, 3, 2, 4, 3, 6]
     *         ]
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int houses = costs[0].length;

        int sum = 0;
        for (int i=1; i<houses; i++) {
            // If the current house painted with color 0, then find the min cost from the previous painted house either in color 1 or 2
            costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        return Math.min(Math.min(costs[houses-1][0], costs[houses-1][1]), costs[houses-1][2]);
    }
}
