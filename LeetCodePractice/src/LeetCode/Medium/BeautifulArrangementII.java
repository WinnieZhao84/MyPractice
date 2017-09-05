package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given two integers n and k, you need to construct a list which contains n different positive integers ranging
 * from 1 to n and obeys the following requirement:
 *
 * Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]
 * has exactly k distinct integers.
 *
 * If there are multiple answers, print any of them.
 *
 * Example 1:
 * Input: n = 3, k = 1
 * Output: [1, 2, 3]
 * Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3,
 * and the [1, 1] has exactly 1 distinct integer: 1.
 *
 * Example 2:
 * Input: n = 3, k = 2
 * Output: [1, 3, 2]
 * Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3,
 * and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 *
 */
public class BeautifulArrangementII {

    public int[] constructArray(int n, int k) {
        if (k >= n) {
            return null;
        }

        int[] res = new int[n];
        for (int i = 0, l = 1, r = n; l <= r; i++) {
            if (k>1) {
                res[i] = k-- % 2!=0 ? l++ : r--;
            }
            else {
                res[i] = k % 2!=0 ? l++ : r--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        BeautifulArrangementII solution = new BeautifulArrangementII();

        System.out.println(Arrays.toString(solution.constructArray(3,2)));
        System.out.println(Arrays.toString(solution.constructArray(3,1)));
    }
}