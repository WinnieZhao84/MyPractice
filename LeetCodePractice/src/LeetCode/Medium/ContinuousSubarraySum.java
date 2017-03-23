package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 *
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Note:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 *
 * Created by WinnieZhao on 2017/3/22.
 */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        /**
         * (a+(n*x))%x is same as (a%x) For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42]
         * and the reminders are [5,1,1,5,0]. We got reminder 5 at index 0 and at index 3. That means, in between these
         * two indexes we must have added a number which is multiple of the k.
         */
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, -1); // Handle cases for only two elements in the array
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer prev = map.get(k == 0 ? sum : sum % k);
            if (prev != null) {
                if (i - prev > 1) {
                    return true;
                }
            }
            else {
                map.put(k == 0 ? sum : sum % k, i);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();

        int[] nums = {23,2,6,4,7};
        System.out.println(solution.checkSubarraySum(nums, 6));
    }
}
