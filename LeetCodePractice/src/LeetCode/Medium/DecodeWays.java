package LeetCode.Medium;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * 
 * @author WinnieZhao
 *
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        char[] chs = s.toCharArray();
        int len = chs.length;
        if (chs[0] == '0') {
            return 0;
        }

        int[] dp = new int[len+1];
        dp[0]=1;
        dp[1]= chs[0] >= '1' && chs[0] <= '9' ? 1 : 0;

        for (int i=2; i<=len; i++) {
            int cur = chs[i-1] - '0';
            int pre = chs[i-2] - '0';

            int sum = pre * 10 + cur;

            if (cur <= 9 && cur >= 1) {
                dp[i] += dp[i-1];
            }
            if (sum >= 10 && sum <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[len];
    }
    
    public static void main(String[] args) {
        DecodeWays solution = new DecodeWays();
        System.out.println(solution.numDecodings("456"));

        System.out.println(solution.numDecodings("123"));
    }
}
