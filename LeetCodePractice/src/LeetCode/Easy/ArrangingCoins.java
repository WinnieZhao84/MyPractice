package LeetCode.Easy;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * 
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * 
 * Example 1:
 * 
 * n = 5
 * 
 * The coins can form the following rows:
 * 
 * ก่
 * ก่ ก่
 * ก่ ก่
 * 
 * Because the 3rd row is incomplete, we return 2. 
 * 
 * Example 2:
 * 
 * n = 8
 * 
 * The coins can form the following rows:
 * 
 * ก่
 * ก่ ก่
 * ก่ ก่ ก่
 * ก่ ก่
 * 
 * Because the 4th row is incomplete, we return 3.

 * @author WinnieZhao
 *
 */
public class ArrangingCoins {

    public int arrangeCoins(int n) {
        if (n < 0)
            return -1;
        else if (n == 0)
            return 0;
        else if (n < 3)
            return 1;
        
        int rows = 2;
        long coins = 3;
        
        // loop until coins is bigger than n.
        while (coins <= n) {
            coins = coins + rows + 1;
            rows++;
        }
        
        // return the last number
        return rows-1;
    }
    
    /**
     * (1 + X) * X = 2n
     * 4X * X + 4 * X = 8n
     * (2X + 1)(2X + 1) - 1 = 8n
     * X = (Math.sqrt(8 * n + 1) - 1)/ 2
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        ArrangingCoins solution = new ArrangingCoins();
        
        System.out.println(solution.arrangeCoins(1804289383));
    }
}
