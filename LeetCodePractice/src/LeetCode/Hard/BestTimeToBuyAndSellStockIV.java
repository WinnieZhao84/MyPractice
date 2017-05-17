package LeetCode.Hard;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 * Created by WinnieZhao on 2017/5/16.
 */
public class BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }

        int length = prices.length;
        if (k >= length/2) {
            return this.noLimit(prices);
        }

        // DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
        int dp[][] = new int[k+1][length];

        for (int i=1; i<=k; i++) {
            int holding = -prices[0];
            for (int j=1; j<length; j++) {
                // gives us the maximum price when we sell at this price
                // dp[i][j - 1] means at this price j we don't sell stock, holding + prices[j] means we sell our current holding stock.
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+holding);
                // holding means we holding a lowest price stock, dp[i - 1][j - 1] means we buy this stock.
                holding = Math.max(dp[i-1][j-1]-prices[j], holding);
            }
        }
        return dp[k][length-1];
    }

    private int noLimit(int[] prices) {
        int max = 0;
        int length = prices.length;
        for (int i=1; i<length; i++) {
            if (prices[i] > prices[i-1]) {
                max += prices[i] - prices[i-1];
            }
        }

        return max;
    }
}
