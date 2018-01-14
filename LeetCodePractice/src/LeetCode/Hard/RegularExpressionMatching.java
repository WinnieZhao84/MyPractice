package LeetCode.Hard;

/**
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true (* matched zero of preceding char, so zero 'c' two 'a' and one 'b')

 * Created by WinnieZhao on 2017/6/20.
 */
public class RegularExpressionMatching {

    public boolean isMatch_DP(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        /**
         * Initial state: dp[0][0] = true, e.g. "" -> "", true.
         * dp[i][0] = false: i >= 1, any string cannot match a empty string
         * dp[0][i], if (p.charAt(j) == '*'), dp[0][j] = dp[0][j - 2]
         */
        for (int i = 1; i <= p.length(); i++){
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for (int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= p.length(); j++){
                char pChar = p.charAt(j-1);
                char sChar = s.charAt(i-1);

                if (pChar != '*'){
                    // When pChar is not "*"
                    // 1) If pChar and sChar is the same, or pChar is ".", dp[i][j] should be same as dp[i-1][j-1]
                    // 2) otherwise it's false
                    if (pChar == sChar || pChar == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = false;
                    }
                }
                else {
                    // When pChar is *, "*" can represent zero or more than 1 preceding chars, so:
                    // 1) If previous J position is not the same as sChar, and it's not a "."
                    //  dp[i][j] should be same s dp[i][j-2] as the suppose "*" represent zero preceding char now
                    if (p.charAt(j-2) != sChar && p.charAt(j-2) != '.'){
                        dp[i][j] = dp[i][j-2];
                    }
                    // 2) dp[i][j - 2]  ||  // zero matched
                    //    dp[i][j - 1] ||   // 1 matched
                    //    dp[i - 1][j]      // 2+ matched
                    else {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
        }


        return dp[s.length()][p.length()];
    }

    // Recursive
    public boolean isMatch(String s, String p) {

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return this.isMatch(s, p.substring(2)) || (firstMatch && this.isMatch(s.substring(1), p));
        }
        else {
            return firstMatch && this.isMatch(s.substring(1), p.substring(1));
        }

    }

    public static void main(String[] args) {
        RegularExpressionMatching solution = new RegularExpressionMatching();

        System.out.println(solution.isMatch_DP("ab", ".*"));
    }
}
