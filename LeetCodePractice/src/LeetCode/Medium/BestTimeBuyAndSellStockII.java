package LeetCode.Medium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times). However, you may not engage in
 *  multiple transactions at the same time (ie, you must sell the stock before you buy again).

 * @author ASUS-PC
 *
 */
public class BestTimeBuyAndSellStockII {
    public int maxProfit(int[] prices) {
    	if (prices.length < 2) return 0;
        
        int maxDiff = 0;
        for (int i=1; i<=prices.length-1; i++) {
            int diff = prices[i] - prices[i-1];
            
            if (diff > 0) {
                maxDiff += diff;
            }
        }
        
        return maxDiff;
    }
    
    public static void main(String[] args) {
        BestTimeBuyAndSellStockII solution = new BestTimeBuyAndSellStockII();
        
        int[] prices = {1, 2, 3, 0, 2};
        
        System.out.println(solution.maxProfit(prices));
    }
}
