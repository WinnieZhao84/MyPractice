package LeetCode.Interview.Microsoft.LeetCode;

/**
 * 727
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input: S = "abcdebdde", T = "bde"
 * Output: "bcde"
 *
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order
 *
 * Note:
 * All the strings in the input will only contain lowercase letters. The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].

 */
public class MinimumWindowSubsequence {

    /**
     * dp[i][j]表示T[0, j]是S[0,i]的subsequence, 所以我们的目标函数就是min(i-dp[i][n-1]) for all i < m
     */
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }

        return len == n + 1 ? "" : S.substring(start, start + len);
    }

    public String minWindow_bruteforce(String S, String T) {
        int min = -1;
        int idx = -1;

        char[] Tc = T.toCharArray();
        char[] Sc = S.toCharArray();

        for(int i = 0;i < S.length();i++){
            if (Sc[i] != Tc[0]) continue;
            int len = check(Tc, Sc, i);
            if (len <= 0) break;
            if (min == -1 || len < min){
                idx = i;
                min = len;
            }
        }
        if (min == -1) return "";
        return S.substring(idx, idx + min);
    }

    public int check(char[] Tc, char[] Sc, int start){
        int i = start, j = 0;
        while(i < Sc.length && j < Tc.length){
            if(Sc[i] == Tc[j]) j++;
            i++;
        }
        if (j == Tc.length) return i - start;

        return -1;
    }
}
