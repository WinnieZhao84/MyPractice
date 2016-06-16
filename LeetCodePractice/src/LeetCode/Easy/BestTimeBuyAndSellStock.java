package LeetCode.Easy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 * design an algorithm to find the maximum profit.

 * @author ASUS-PC
 *
 */
public class BestTimeBuyAndSellStock {
    public int maxProfit(int[] prices) {
    	if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        
        int low = prices[0];
        
        int result = 0;
        for (int i=1; i<=length-1; i++) {
        	if (prices[i] < low) {
        		low = prices[i];
        	}
        	else if(prices[i] - low > result) {
        		result = prices[i] - low;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	BestTimeBuyAndSellStock solution = new BestTimeBuyAndSellStock();
        
    	int[] prices = {2,1,1};
        System.out.println(solution.maxProfit(prices));
    }
}
