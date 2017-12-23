package LeetCode.Medium;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide
 * this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 *
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.

 * Created by WinnieZhao on 10/21/2017.
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;

        boolean[] visited = new boolean[nums.length];
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == target) {
                k--;
                visited[i] = true;
            }
        }

        return helper(nums, k, target, visited, 0, 0);
    }

    private boolean helper(int[] nums, int k, int target, boolean[] visited, int start, int sum) {
        if (k==0) {
            return true;
        }

        if (sum == target) {
            return this.helper(nums, k-1, target, visited, 0, 0);
        }

        for (int i=start; i<nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (this.helper(nums, k, target, visited, i+1, sum+nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets solution = new PartitionToKEqualSumSubsets();

        int[] nums = {2,2,2,2,3,4,5};
        System.out.println(solution.canPartitionKSubsets(nums, 4));
    }
}
