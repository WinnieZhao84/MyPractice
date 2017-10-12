package LeetCode.Medium;

import java.util.*;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs.
 * You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 *
 * Explanation: The longest chain is [1,2] -> [3,4]
 *
 * Note:The number of given pairs will be in the range [1, 1000].

 * Created by WinnieZhao on 2017/7/23.
 */
public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {

        if (pairs == null || pairs.length == 0) {
            return 0;
        }

        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));

        int len = pairs.length;

        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i=0; i<len; i++) {
            int[] cur = pairs[i];

            for (int j=0; j<i; j++) {
                int[] pre = pairs[j];

                if (cur[0] > pre[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len-1];
    }

    public static void main(String[] args) {
        MaximumLengthOfPairChain solution = new MaximumLengthOfPairChain();

        int[][] pair = {{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};

        System.out.println(solution.findLongestChain(pair));
    }
}
