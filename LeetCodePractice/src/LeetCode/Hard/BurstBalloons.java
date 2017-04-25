package LeetCode.Hard;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example: Given [3, 1, 5, 8]
 * Return 167
 *
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5     +  3*5*8    +  1*3*8      + 1*8*1   = 167

 * Created by WinnieZhao on 4/21/2017.
 */
public class BurstBalloons {

    public int DP(int i, int j, int[] coins, int[][] dp) {
        if (dp[i][j] > 0) return dp[i][j];
        for (int x = i; x <= j; x++) {
            dp[i][j] = Math.max(dp[i][j], DP(i, x - 1, coins, dp) + coins[i - 1] * coins[x] * coins[j + 1] + DP(x + 1, j, coins, dp));
        }
        return dp[i][j];
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] coins = new int[n + 2];

        for (int i = 0; i < n; i++) {
            coins[i + 1] = nums[i];
        }

        coins[0] = coins[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];

        return DP(1, n, coins, dp);
    }
}
