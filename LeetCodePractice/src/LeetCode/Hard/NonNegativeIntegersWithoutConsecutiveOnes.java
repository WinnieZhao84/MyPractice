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

    public int findIntegers_better(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int len = sb.length();

        int[] a = new int[len];
        int[] b = new int[len];

        /**
         * a[i] stands for how many numbers if bit i is 0.
         * b[i] stands for how many numbers if bit i is 1.
         * The value of 1 only comes from previous 0. No consecutive ones!
         * However, the value of 0 comes from previous 0 and 1.
         */
        a[0]=1; b[0]=1;
        for (int i=1; i<len; i++) {
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }

        int result = a[len-1] + b[len-1];

        for (int i=len-2; i>=0; i--) {
            if (sb.charAt(i) == '1' && sb.charAt(i+1) == '1') {
                break;
            }
            if (sb.charAt(i) == '0' && sb.charAt(i+1) == '0') {
                result -= b[i];
            }
        }

        return result;

    }

    public static void main(String[] args) {
        NonNegativeIntegersWithoutConsecutiveOnes solution = new NonNegativeIntegersWithoutConsecutiveOnes();
        solution.findIntegers(8);
    }
}
