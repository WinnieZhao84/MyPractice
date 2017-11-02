package LeetCode.Medium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * 
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * 
 * Example:
 * 
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 * 
 * @author WinnieZhao
 *
 */
public class BestTimeBuyAndSellStockWithCooldown {

    /**
     * The series of problems are typical dp. The key for dp is to find the variables to represent the states and deduce the transition function.
     *
     * Of course one may come up with a O(1) space solution directly, but I think it is better to be generous when you think and be greedy when you implement.
     *
     * The natural states for this problem is the 3 possible transactions : buy, sell, rest. Here rest means no transaction on that day (aka cooldown).
     * Then the transaction sequences can end with any of these three states.
     *
     * For each of them we make an array, buy[n], sell[n] and rest[n].
     *
     * buy[i] means before day i what is the maxProfit for any sequence end with buy.
     * sell[i] means before day i what is the maxProfit for any sequence end with sell.
     * rest[i] means before day i what is the maxProfit for any sequence end with rest.
     *
     * Then we want to deduce the transition functions for buy sell and rest. By definition we have:
     *
     * buy[i]  = max(rest[i-1]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
     * Where price is the price of day i. All of these are very straightforward. They simply represents :
     *
     * (1) We have to `rest` before we `buy` and
     * (2) we have to `buy` before we `sell`
     * One tricky point is how do you make sure you sell before you buy, since from the equations it seems that [buy, rest, buy] is entirely possible.
     *
     * Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] = max(sell[i-1], rest[i-1]).
     * That made sure [buy, rest, buy] is never occurred.
     *
     * A further observation is that and rest[i] <= sell[i] is also true therefore
     *
     * rest[i] = sell[i-1]
     * Substitute this in to buy[i] we now have 2 functions instead of 3:
     *
     * buy[i] = max(sell[i-2]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     *
     * This is better than 3, but we can do even better Since states of day i relies only on i-1 and i-2 we can reduce the O(n) space to O(1).

     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    public class Solution {

        public int maxProfit(int[] prices) {
            if (prices.length < 2) return 0;

            int[] sell = new int[prices.length];
            int[] buy = new int[prices.length];

            buy[0] = -prices[0]; // buy in 0;
            buy[1] = Math.max(-prices[0], -prices[1]); // what if buy in 1
            sell[0] = 0; // can not sell in 0.
            sell[1] = Math.max(buy[0]+prices[1], 0); // sell 1 if buy in 0

            for (int i=2; i<prices.length; i++) {
                buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
                sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);

            }
            return sell[prices.length-1];
        }
    }

    public static void main(String[] args) {
        BestTimeBuyAndSellStockWithCooldown solution = new BestTimeBuyAndSellStockWithCooldown();
        
        int[] prices = {1, 2, 3, 0, 2};
        
        System.out.println(solution.maxProfit(prices));
        
        int[] prices1 = {1, 2, 4};
        
        System.out.println(solution.maxProfit(prices1));
    }
}
