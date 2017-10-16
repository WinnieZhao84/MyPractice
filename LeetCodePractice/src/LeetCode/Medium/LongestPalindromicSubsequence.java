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

        /**
         * The order can be decided from the state transition.
         * dp[i][j] is depend on dp[i+1][j-1], which means to calculate dp[i][j],
         * you need to know the value of dp[i+1][j-1] first
         * ("i" is relying on "i+1", "i+1" need to be calculate first, that's why the i loop reverse order)
         */
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

    // Recursive solution
    public class Solution {
        public int longestPalindromeSubseq(String s) {
            return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
        }

        private int helper(String s, int i, int j, Integer[][] memo) {
            if (memo[i][j] != null) {
                return memo[i][j];
            }
            if (i > j) return 0;
            if (i == j) return 1;

            if (s.charAt(i) == s.charAt(j)) {
                memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
            } else {
                memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
            }
            return memo[i][j];
        }
    }
    
    
    public static void main(String[] args) {
        LongestPalindromicSubsequence solution = new LongestPalindromicSubsequence();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }
}
