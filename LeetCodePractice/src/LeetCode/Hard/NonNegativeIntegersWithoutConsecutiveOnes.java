package LeetCode.Hard;

/**
 * Given a positive integer n, find the number of non-negative integers less than or equal to n,
 * whose binary representations do NOT contain consecutive ones.
 *
 * Example 1:
 * Input: 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 *
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Note: 1 <= n <= 10^9

 * Created by WinnieZhao on 5/31/2017.
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {

    // Time Limit Exceeded
    public int findIntegers(int num) {
        int count = 0;
        for (int i = 0; i <= num; i++)
            if (check(i))
                count++;
        return count;
    }

    private boolean check(int n) {
        int i = 31;
        while (i > 0) {
            if ((n & (1 << i)) != 0 && (n & (1 << (i - 1))) != 0)
                return false;
            i--;
        }
        return true;
    }

    public static void main(String[] args) {
        NonNegativeIntegersWithoutConsecutiveOnes solution = new NonNegativeIntegersWithoutConsecutiveOnes();
        solution.findIntegers(8);
    }
}
