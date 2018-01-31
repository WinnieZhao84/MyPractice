package LeetCode.Medium;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
 * and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Note:
 * 0 < prices.length <= 50000
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.

 * Created by WinnieZhao on 10/22/2017.
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        int[] hold = new int[len];
        int[] sold = new int[len];

        hold[0] = -prices[0];
        for (int i=1; i<len; i++) {
            hold[i] = Math.max(hold[i-1], sold[i-1] - prices[i]);
            sold[i] = Math.max(sold[i-1], hold[i-1] + prices[i] - fee);
        }

        return sold[len-1];
    }

    /**
     * At the end of the i-th day, we maintain cash, the maximum profit we could have if we did not have a share of stock,
     * and hold, the maximum profit we could have if we owned a share of stock.
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit_better(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            hold = Math.max(hold, cash - prices[i]);
            cash = Math.max(cash, hold + prices[i] - fee);
        }
        return cash;
    }
}
