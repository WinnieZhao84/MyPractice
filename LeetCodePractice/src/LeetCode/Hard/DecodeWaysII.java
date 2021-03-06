package LeetCode.Hard;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 *
 * Note:
 * The length of the input string will fit in range [1, 10^5].
 * The input string will only contain the character '*' and digits '0' - '9'.

 * Created by WinnieZhao on 7/10/2017.
 */
public class DecodeWaysII {
    int M = 1000000007;

    public int numDecodings(String s) {
        long[] dp = new long[s.length()+1];
        dp[0] = 1;

        if (s.charAt(0) == '0'){
            return 0;
        }

        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        for(int i = 2; i <= s.length(); i++){
            char pre = s.charAt(i-2);
            char cur = s.charAt(i-1);

            // For dp[i-1]
            if (cur == '*'){
                dp[i] += 9 * dp[i-1];
            }
            else if (cur > '0'){
                dp[i] += dp[i-1];
            }

            // For dp[i-2]
            if (pre == '*'){
                if (cur == '*') {
                    dp[i] += 15 * dp[i-2];
                }
                else if (cur <= '6') {
                    dp[i] += 2 * dp[i-2];
                }
                else {
                    dp[i] += dp[i-2];
                }
            }
            else if (pre == '1' || pre == '2'){
                if (cur == '*') {
                    if (pre == '1'){
                        dp[i] += 9 * dp[i-2];
                    }
                    else {
                        dp[i] += 6 * dp[i-2];
                    }
                }
                else if( ((pre - '0')*10 + (cur - '0')) <= 26 ) {
                    dp[i] += dp[i-2];
                }
            }

            dp[i] %= M;
        }
        return (int) dp[s.length()];
    }

    public static void main(String[] args) {
        DecodeWaysII solution = new DecodeWaysII();
        System.out.println(solution.numDecodings("**"));
    }
}
