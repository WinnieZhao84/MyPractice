package LeetCode.Medium;

/**
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays
 * where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 *
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 *
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 *
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.

 * Created by WinnieZhao on 10/22/2017.
 */
public class SubarrayProductLessThanK {

    /**
     * Time Complexity: O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            if (product < k) {
                result++;
                for (int j = i + 1; j < nums.length; j++) {
                    product *= nums[j];
                    if (product < k) {
                        result++;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(n)
     *
     * Slide window solution:
     * Our loop invariant is that left is the smallest value so that the product in the window
     * prod = nums[left] * nums[left + 1] * ... * nums[right] is less than k.
     * For every right, we update left and prod to maintain this invariant. Then, the number of intervals
     * with subarray product less than k and with right-most coordinate right, is right - left + 1.
     * We'll count all of these for each value of right.
     *
     * When we decide the window size, we can calculate the sub array count, the sub array count for the
     * window size is just (right-left+1).
     * For example: [5 2 6], when the right most is at position of 6, the count of the sub array is the
     * window size which includes the right most num 6 => the sub array count is [6], [2,6], [5,2,6]
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK_better(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }

        int left = 0;
        int res = 0;
        int product = 1;

        for (int right=0; right<nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left++];
            }

            res += right-left+1;
        }

        return res;
    }

}
