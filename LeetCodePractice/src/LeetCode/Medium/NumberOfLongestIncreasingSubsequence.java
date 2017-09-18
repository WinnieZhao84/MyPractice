package LeetCode.Medium;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

 * Created by WinnieZhao on 9/17/2017.
 */
public class NumberOfLongestIncreasingSubsequence {

    /**
     * The idea is to use two arrays dp[n] and cnt[n] to record the maximum length of Increasing Subsequence
     * and the coresponding number of these sequence which ends with nums[i], respectively. That is:
     *
     * dp[i]: the length of the Longest Increasing Subsequence which ends with nums[i].
     * cnt[i]: the number of the Longest Increasing Subsequence which ends with nums[i].
     *
     * Then, the result is the sum of each cnt[i] while its corresponding dp[i] is the maximum length

     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max_length = 0;
        int res = 0;

        int length = nums.length;
        int[] dp = new int[length];
        int[] cnt = new int[length];

        dp[0] = 1;
        for (int i=0; i<nums.length; i++) {
            dp[i] = 1;
            cnt[i] = 1;

            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] == dp[j] + 1) {
                        cnt[i] += cnt[j];
                    }
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }

            if (max_length == dp[i]) {
                res += cnt[i];
            }
            if (max_length < dp[i]) {
                max_length = dp[i];
                res = cnt[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence solution = new NumberOfLongestIncreasingSubsequence();

        int[] nums = {1,3,5,4,7};
        System.out.println(solution.findNumberOfLIS(nums));
    }
}
