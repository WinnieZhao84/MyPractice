package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 325
 *
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

 * Created by WinnieZhao on 2017/4/6.
 */
public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        // save sum from 0 to i, and sum is key,
        Map<Integer, Integer> map = new HashMap<>();
        int len = Integer.MIN_VALUE;
        int sum = 0;
        map.put(0, -1);// This is very important, if any number range sum[i..j] == 0, then without this line,
                       //j-i is not count as the max len, this line is used to offset the [i...j] range.
        for(int i= 0; i< nums.length; i++){
            sum += nums[i];
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
            if( map.containsKey(sum - k)){
                len = Math.max(len, i - map.get(sum - k));
            }
        }

        return len == Integer.MIN_VALUE ? 0 : len;
    }

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK solution = new MaximumSizeSubarraySumEqualsK();

        int[] nums = {-2, -1, 2, 1};
        System.out.println(solution.maxSubArrayLen(nums, 1));
    }
}
