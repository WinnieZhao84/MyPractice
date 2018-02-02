package LeetCode.Interview.Facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 325
 *
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 *
 * Created by WinnieZhao on 2/2/2018.
 */
public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);

        int max=Integer.MIN_VALUE;
        int sum=0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];

            if (!indexMap.containsKey(sum)) {
                indexMap.put(sum, i);
            }
            if (indexMap.containsKey(sum - k)) {
                max = Math.max(max, i - indexMap.get(sum - k));
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK solution = new MaximumSizeSubarraySumEqualsK();

        System.out.println(solution.maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
        System.out.println(solution.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }
}
