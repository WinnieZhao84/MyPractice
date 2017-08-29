package LeetCode.Hard;

/**
 *
 * There is a strange printer with the following two special requirements:
 *
 * The printer can only print a sequence of the same character each time.
 *
 * At each turn, the printer can print new characters starting from and ending at any places,
 * and will cover the original existing characters.
 *
 * Given a string consists of lower English letters only, your job is to count the minimum number
 * of turns the printer needed in order to print it.
 *
 * Example 1:
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 *
 * Example 2:
 * Input: "aba"
 * Output: 2
 *
 * Explanation: Print "aaa" first and then print "b" from the second place of the string,
 * which will cover the existing character 'a'.
 *
 * Hint: Length of the given string will not exceed 100.

 * Created by WinnieZhao on 8/21/2017.
 */
public class StrangePrinter {

    public int strangePrinter(String s) {
        int[][] dp = new int[101][101];
        int n = s.length();
        if (n == 0) return 0;
        char[] cs = s.toCharArray();

        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; --i){
            for (int j = i + 1; j < n; ++j){
                dp[i][j] = dp[i + 1][j] + 1;
                char c = cs[i];
                for (int k = i; k < j; ++k){
                    if (cs[k + 1] == c) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] - 1);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

}
