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
        
        if (s == null) {
            return 0;
        }
        if(s.length() <= 1) {
            return s.length();
        }
        int length = s.length();
        
        int dp[][] = new int[length][length]; 
        
        for(int i=0;i<s.length();i++){
            dp[i][i] = 1;
        }
        
        for (int len=2; len<=s.length(); len++) {
            
            for (int i=0; i<s.length() - len +1; i++) {
                int j = i + len -1;
                
                if (s.charAt(i) == s.charAt(j) && len == 2) {
                    dp[i][j] = 2;
                }
                else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[0][length-1];
        
    }
    
    
    public static void main(String[] args) {
        LongestPalindromicSubsequence solution = new LongestPalindromicSubsequence();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }
}
