package LeetCode.Medium;

import java.util.HashMap;

/**
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 * Created by WinnieZhao on 2017/5/1.
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap <Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i=0; i<nums.length; i++) {
            sum += nums[i];

            // if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.

            if (map.containsKey(sum-k)) {
                count += map.get(sum-k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            }
            else {
                map.put(sum, 1);
            }
        }

        return count;

    }
}
