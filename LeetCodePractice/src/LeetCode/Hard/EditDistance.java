package LeetCode.Hard;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character

 * Created by WinnieZhao on 2017/5/16.
 */
public class EditDistance {

    /**
     * This is a classic problem of Dynamic Programming.
     * We define the state dp[i][j] to be the minimum number of operations to convert word1[0..i - 1] to word2[0..j - 1].
     * The state equations have two cases: the boundary case and the general case.
     * Note that in the above notations, both i and j take values starting from 1.
     *
     * For the boundary case, that is, to convert a string to an empty string, it is easy to see that the minimum number
     * of operations to convert word1[0..i - 1] to "" requires at least i operations (deletions).
     * In fact, the boundary case is simply:
     *
     * dp[i][0] = i;
     * dp[0][j] = j.
     *
     * Now let's move on to the general case, that is, convert a non-empty word1[0..i - 1] to another non-empty
     * word2[0..j - 1]. Well, let's try to break this problem down into smaller problems (sub-problems).
     * Suppose we have already known how to convert word1[0..i - 2] to word2[0..j - 2], which is dp[i - 1][j - 1].
     * Now let's consider word[i - 1] and word2[j - 1]. If they are equal, then no more operation is needed
     * and dp[i][j] = dp[i - 1][j - 1]
     *
     * Case 1: word1[i] == word2[j], i.e. the ith the jth character matches
     *         dp[i][j] = dp[i - 1][j - 1]
     *
     * Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper
     *         dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1)
     *         dp[i][j - 1] represents insert operation
     *         dp[i - 1][j] represents delete operation
     *         dp[i - 1][j - 1] represents replace operation
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        if (word1.length() == 0 || word2.length() == 0) return word1.length() == 0 ? word2.length() : word1.length();

        int len1 = word1.length();
        int len2 = word2.length();

        int[][]dp = new int[len1+1][len2+1];
        for (int i=0; i<=len1;i++) {
            for (int j=0;j<=len2;j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = word1.charAt(i-1) == word2.charAt(j-1) ? dp[i-1][j-1] :
                            Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }

        return dp[len1][len2];

    }

}
