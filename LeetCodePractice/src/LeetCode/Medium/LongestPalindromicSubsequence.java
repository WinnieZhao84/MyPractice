package LeetCode.Medium;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input: "bbbab"
 * Output: 4
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * Example 2:
 * Input: "cbbd"
 * Output: 2
 * One possible longest palindromic subsequence is "bb".
 * 
 * @author WinnieZhao
 *
 */
public class LongestPalindromicSubsequence {
    
    // http://algorithms.tutorialhorizon.com/longest-palindromic-subsequence/
    public int longestPalindromeSubseq(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        if (len == 0) {
            return 1;
        }

        /**
         * dp[i][j]: the longest palindromic subsequence length of substring(i, j)
         *
         * State transition:
         * Initialization: dp[i][i] = 1
         * dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
         * otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
         *
         */
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();

        for (int i=len-1; i>=0; i--) {
            dp[i][i] = 1;

            for (int j=i+1; j<len; j++) {

                if (chs[i] == chs[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][len-1];
        
    }
    
    
    public static void main(String[] args) {
        LongestPalindromicSubsequence solution = new LongestPalindromicSubsequence();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }
}
