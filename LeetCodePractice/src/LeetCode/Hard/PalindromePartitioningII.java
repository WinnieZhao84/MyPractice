package LeetCode.Hard;

import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

 * Created by WinnieZhao on 2017/5/24.
 */
public class PalindromePartitioningII {

    /**
     * Calculate and maintain 2 DP states:
     *
     * pal[i][j] , which is whether s[i..j] forms a pal
     * d[i], which is the minCut for s[i..n-1]
     *
     * Once we comes to a pal[i][j]==true:
     * if j==n-1, the string s[i..n-1] is a Pal, minCut is 0, d[i]=0;
     * else: the current cut num (first cut s[i..j] and then cut the rest s[j+1...n-1]) is 1+d[j+1], compare it to the existing minCut num
     *       d[i], replace if smaller. d[0] is the answer.

     * @param s
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.isEmpty()) return 0;

        int length = s.length();
        boolean[][] palindrome= new boolean[length][length];

        for (int i=0;i<length;i++) {
            Arrays.fill(palindrome[i], false);
        }

        int[] results = new int[length];
        for (int start=length-1; start>=0; start--) {
            results[start] = length - start - 1;

            for (int end=start; end<length; end++) {

                if (s.charAt(start) == s.charAt(end)) {
                    if (end-start < 2) //Refer to these two cases: xx OR xyx
                        palindrome[start][end]=true;
                    else //Depends on the inner substring,if the inner substring is palindrome,the outer is
                        palindrome[start][end]=palindrome[start+1][end-1];
                }

                if (palindrome[start][end]) {
                    if (end==length-1) {
                        results[start] = 0;
                    }
                    else {
                        results[start] = Math.min(results[start], results[end + 1] + 1);
                    }
                }

            }
        }

        return results[0];
    }
}
