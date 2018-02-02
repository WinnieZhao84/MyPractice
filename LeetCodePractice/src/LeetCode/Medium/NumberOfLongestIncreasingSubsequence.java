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
     * and the corresponding number of these sequence which ends with nums[i], respectively. That is:
     *
     * lens[i]: the length of the Longest Increasing Subsequence which ends with nums[i].
     * cnts[i]: the number of the Longest Increasing Subsequence which ends with nums[i].
     *
     * Then, the result is the sum of each cnt[i] while its corresponding dp[i] is the maximum length

     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return len;
        }

        int res = 0;
        int[] lens = new int[len];
        int[] cnts = new int[len];

        int maxLen=1;

        for (int i=0; i<len; i++) {
            lens[i]=1;
            cnts[i]=1;

            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {

                    if (lens[i] < lens[j]+1) {
                        cnts[i] = cnts[j];
                    }
                    else if (lens[i] == lens[j]+1) {
                        cnts[i] += cnts[j];
                    }

                    lens[i] = Math.max(lens[j]+1, lens[i]);
                }
            }

            if (maxLen == lens[i]) {
                res += cnts[i];
            }
            else if (maxLen < lens[i]) {
                res = cnts[i];
                maxLen = lens[i];
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
