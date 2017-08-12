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

        List<Pair> list = new ArrayList<>();
        for (int[] num : pairs) {
            Pair pair = new Pair(num);
            list.add(pair);
        }

        Collections.sort(list);

        int length = list.size();
        int dp[] = new int[length];
        dp[0] = 1;

        for (int i=1; i<length; i++) {
            Pair pair = list.get(i);

            dp[i] = dp[i-1];
            for (int j=i-1; j>=0; j--) {
                Pair pre = list.get(j);

                if (pair.first > pre.second) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }

        return dp[length-1];
    }

    class Pair implements Comparable<Pair> {
        int first;
        int second;

        @Override
        public int compareTo(Pair o) {
            if (this.first != o.first) {
                return this.first - o.first;
            }
            else {
                return this.second - o.second;
            }
        }

        Pair(int[] pair) {
            this.first = pair[0];
            this.second = pair[1];
        }
    }

    public static void main(String[] args) {
        MaximumLengthOfPairChain solution = new MaximumLengthOfPairChain();

        int[][] pair = {{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};

        System.out.println(solution.findLongestChain(pair));
    }
}
