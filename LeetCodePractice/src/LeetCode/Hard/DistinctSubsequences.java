package LeetCode.Hard;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.

 * Created by WinnieZhao on 6/25/2017.
 */
public class DistinctSubsequences {

    /**
     * we will build an array mem where mem[i+1][j+1] means that S[0..j] contains T[0..i] that many times
     * as distinct subsequences. Therefor the result will be mem[T.length()][S.length()].
     *
     * we can build this array rows-by-rows:
     * the first row must be filled with 1. That's because the empty string is a subsequence of any string but only 1 time.
     * So mem[0][j] = 1 for every j. So with this we not only make our lives easier, but we also return correct value
     * if T is an empty string.
     * the first column of every rows except the first must be 0. This is because an empty string cannot contain a non-empty
     * string as a substring -- the very first item of the array: mem[0][0] = 1, because an empty string contains the empty
     * string 1 time.
     *
     * So the matrix looks like this:
     *   S 0123....j
     * T +----------+
     *   |1111111111|
     * 0 |0         |
     * 1 |0         |
     * 2 |0         |
     * . |0         |
     * . |0         |
     * i |0         |

     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {

        int[][] dp = new int[t.length()+1][s.length()+1];

        for (int i=0; i<s.length(); i++) {
            dp[0][i] = 1;
        }

        for (int i=0; i<t.length();i++) {
            for (int j=0; j<s.length();j++) {
                // If last characters don't match, then value
                // is same as the value without last character
                // in S.
                // Else value is obtained considering two cases.
                // a) All substrings without last character in S
                // b) All substrings without last characters in both.
                if (t.charAt(i) == s.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + dp[i+1][j];
                }
                else {
                    dp[i+1][j+1] = dp[i+1][j];
                }
            }
        }

        return dp[t.length()][s.length()];
    }
}
