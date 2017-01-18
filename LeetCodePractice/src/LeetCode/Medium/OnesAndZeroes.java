package LeetCode.Medium;

/**
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * 
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * 
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * 
 * Example 1:
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3 
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are ��10,��0001��,��1��,��0��
 * 
 * Example 2:
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 * @author WinnieZhao
 *
 */
public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        
        for (String s : strs) {
            int[] count = count(s);
            System.out.println(s);
            System.out.println("=======");
            for (int i=m;i>=count[0];i--) {
                for (int j=n;j>=count[1];j--) {
                    dp[i][j] = Math.max(1 + dp[i-count[0]][j-count[1]], dp[i][j]);
                    System.out.println("dp["+i+"]"+"["+j+"]"+" = Max of (" + "1 + " + "dp[" + (i-count[0]) +"][" + (j-count[1]) +"] , dp["+i+"]"+"["+j+"])");
                    System.out.println(dp[i][j]);
                }
            }
            System.out.println("=======");
        }
        return dp[m][n];
    }
    
    private int[] count(String str) {
        int[] count = new int[2];
        for (int i=0; i<str.length(); i++) {
            count[str.charAt(i) - '0']++;
        }

        return count;
    }
    
    public static void main(String[] args) {
        OnesAndZeroes solution = new OnesAndZeroes();
        String[] strs = {"10", "0001", "11001", "1", "0"};
        System.out.println(solution.findMaxForm(strs, 5, 3));
    }
}
