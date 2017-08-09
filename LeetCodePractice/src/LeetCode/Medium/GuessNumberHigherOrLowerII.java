package LeetCode.Medium;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * 
 * Example:
 * n = 10, I pick 8.
 * 
 * First round:  You guess 5, I tell you that it's higher. You pay $5
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * 
 * Game over. 8 is the number I picked.
 * 
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n >= 1, find out how much money you need to have to guarantee a win.

 * @author WinnieZhao
 *
 */
public class GuessNumberHigherOrLowerII {

    public int getMoneyAmount(int n) {
        int[][] table = new int[n + 1][n + 1];
        return payForRange(table, 1, n);
    }
    
    
    //return the amount paid for the game within range [start,end]
    private int payForRange(int[][] dp, int start, int end) {
        if (start >= end)
            return 0;
      
        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int minimumForCurrentRange = Integer.MAX_VALUE;
        for (int x = start; x <= end; ++x) {
            //calculate the amount to pay if pick x.
            int pay = x + Math.max(payForRange(dp, start, x - 1), payForRange(dp, x + 1, end));
            //calculate min of maxes
            minimumForCurrentRange = Math.min(minimumForCurrentRange, pay);
        }

        dp[start][end] = minimumForCurrentRange;
      
        return minimumForCurrentRange;
    }
}
