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
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        char[] chars = s.toCharArray();

        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = chars[0] != '0' ? 1 : 0;

        for (int i=2; i<=chars.length; i++) {
            int pre = chars[i-2] - '0';
            int cur = chars[i-1] - '0';

            if (cur >= 1 && cur <= 9) {
                dp[i] += dp[i-1];
            }

            int val = pre * 10 + cur;
            if (val >= 10 && val <= 26) {
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
