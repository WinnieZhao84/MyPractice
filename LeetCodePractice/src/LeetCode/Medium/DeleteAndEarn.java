package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points.
 * After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 * Example 2:
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 *
 * Note:
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].

 * Created by WinnieZhao on 12/31/2017.
 */
public class DeleteAndEarn {

    /**
     * Time: O(M+N)
     * Space: O(N)
     *
     * M: the length of input array
     * N: the range of the value of each int element
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sum = new int[10001];
        for (int num : nums) {
            sum[num] += num;
        }

        int[] dp = new int[10001];
        dp[1] = sum[1];
        for (int i=2; i<10001; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sum[i]);
        }

        return dp[10000];
    }

    public static void main(String[] args) {
        DeleteAndEarn solution = new DeleteAndEarn();

        System.out.println(solution.deleteAndEarn(new int[] {1,1,1,2,4,5,5,5,6}));
        System.out.println(solution.deleteAndEarn(new int[] {3, 4, 2}));
        System.out.println(solution.deleteAndEarn(new int[] {2, 2, 3, 3, 3, 4}));
    }
}
