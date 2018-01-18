package LeetCode.Hard;

import java.util.Arrays;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 *
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 *
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 *
 * Example:
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 *
 * Note: nums.length will be between 1 and 20000.
 *       nums[i] will be between 1 and 65535.
 *       k will be between 1 and floor(nums.length / 3).

 * Created by WinnieZhao on 1/17/2018.
 */
public class MaximumSumOf3NonOverlappingSubarrays {

    /**
     * The question asks for three non-overlapping intervals with maximum sum of all 3 intervals.
     * If the middle interval is [i, i+k-1], where k <= i <= n-2k, the left interval has to be in
     * sub range [0, i-1], and the right interval is from sub range [i+k, n-1].
     *
     * So the following solution is based on DP.
     * Left[i] is the starting index for the left interval in range [0, i];
     * Right[i] is the starting index for the right interval in range [i, n-1];
     * Then we test every possible starting index of middle interval, i.e. k <= i <= n-2k,
     * and we can get the corresponding left and right max sum intervals easily from DP. And the run time is O(n).
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        int n = nums.length;
        int[] sums = new int[n+1];
        sums[0]=0;
        for (int i=1; i<=n; i++) {
            sums[i] = sums[i-1] + nums[i-1];
        }

        int[] left = new int[n];
        int[] right = new int[n];

        int max=Integer.MIN_VALUE;
        for (int i=k-1; i<n; i++) {
            int sum = sums[i+1] - sums[i+1-k];
            if (sum > max) {
                max = sum;
                left[i] = i+1-k;
            }
            else {
                left[i] = left[i-1];
            }
        }

        max=Integer.MIN_VALUE;
        for (int i=n-k; i>=0; i--) {
            int sum = sums[i+k] - sums[i];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
            else {
                right[i] = right[i+1];
            }
        }

        int[] res = new int[3];

        max=Integer.MIN_VALUE;
        for (int i=k; i<=n-2*k; i++) {
            int l=left[i-1];
            int r=right[i+k];

            int total = (sums[i+k] - sums[i]) + (sums[l+k] - sums[l]) + (sums[r+k] - sums[r]);

            if (total > max) {
                max = total;
                res = new int[] {l, i, r};
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MaximumSumOf3NonOverlappingSubarrays solution = new MaximumSumOf3NonOverlappingSubarrays();

        System.out.println(Arrays.toString(solution.maxSumOfThreeSubarrays(new int[] {1,2,1,2,6,7,5,1}, 2)));
    }
}
