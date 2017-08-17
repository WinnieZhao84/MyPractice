package LeetCode.Medium;

/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 *
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 *
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
 * Output the minimum number of steps to get n 'A'.
 *
 * Example 1:
 * Input: 3
 * Output: 3
 *
 * Explanation:
 * Initially, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * Note:
 * The n will be in the range [1, 1000].

 * Created by WinnieZhao on 7/31/2017.
 */
public class TwoKeysKeyboard {

    /**
     * The algorithm iterates through the factors backwards, selecting the greatest one first.
     * This ensures that the amount of copy/paste operations is minimal. For instance, consider the number 8.
     *
     * You can do it in three ways:
     * Copy (1A), paste (2A), paste (3A), paste (4A), paste (5A), paste (6A), paste (7A), paste (8A)
     * Copy (1A), paste (2A), copy (2A), paste (4A), paste (6A), paste (8A)
     * Copy (1A), paste (2A), paste (2A), copy (4A), paste (8A)
     *
     * Because 4 is 8's highest factor, it will give the optimal solution.

     * @param n
     * @return
     */
    public int minSteps(int n) {

        int[] dp = new int[n+1];
        for (int i=2; i<=n; i++) {
            dp[i] = i;
            for (int j=i-1; j>1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i/j);
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        TwoKeysKeyboard solution = new TwoKeysKeyboard();
        System.out.println(solution.minSteps(5));
        System.out.println(solution.minSteps(8));
    }
}
