package LeetCode.Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Note:The given number is in the range [0, 10^8]

 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        if (num == 0) {
            return 0;
        }
        String str = String.valueOf(num);

        char[] chars = str.toCharArray();

        int[] buckets = new int[10];
        for (int i=0; i<chars.length; i++) {
            buckets[chars[i]-'0'] = i;
        }

        for (int i=0; i<chars.length; i++) {
            int digit = chars[i] - '0';
            for (int k=9; k>digit; k--) {
                if (buckets[k] > i) {
                    char temp = chars[i];
                    chars[i] = chars[buckets[k]];
                    chars[buckets[k]] = temp;

                    return Integer.valueOf(new String(chars));
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        MaximumSwap solution = new MaximumSwap();

        System.out.println(solution.maximumSwap(120));
        System.out.println(solution.maximumSwap(1993));
        System.out.println(solution.maximumSwap(2736));
        System.out.println(solution.maximumSwap(9973));
        System.out.println(solution.maximumSwap(98368));
    }
}
