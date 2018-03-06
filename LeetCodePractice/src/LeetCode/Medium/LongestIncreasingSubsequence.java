package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example, 
 * 
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?

 * @author WinnieZhao
 *
 */
public class LongestIncreasingSubsequence {

    /**
     * Binary search O(nlogn)
     *
     * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
     * We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array
     * to find the one needs update.
     *
     * Each time we only do one of the two:
     * (1) if x is larger than all tails, append it, increase the size by 1
     * (2) if tails[i-1] < x <= tails[i], update tails[i]
     * Doing so will maintain the tails invariant. The the final answer is just the size.
     *
     * The key idea is to keep each Increasing subsequence as long as possible. This means two things:
     * 1) When we find a smaller number than my tail. I need to update my tail. This makes it possible to append more
     * data to this sequence.
     * 2) If we find that the number is smaller than all the subsequence's tail, this means we could create a new subsequence
     * with bigger size with this number as the tail

     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[] tails = new int[len];

        int size = 0;
        for (int num : nums) {
            int i=0;
            int j=size;

            while (i < j) {
                int mid = i + (j-i)/2;

                if (tails[mid] < num) {
                    i = mid+1;
                }
                else {
                    j = mid;
                }
            }

            tails[i] = num;
            if (i == size) {
                size++;
            }
        }

        return size;
    }

    /**
     * Time Complexity: O(n^2)
     * @param nums
     * @return
     */
    public int lengthOfLIS_DP(int[] nums) {
        
        if (nums == null || nums.length <= 1) return nums.length;
        
        int[] dp = new int[nums.length];

        int max = 1;
        for(int i=0; i<nums.length;i++){
            dp[i]=1;
            
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(dp[i], max);
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] nums = {4,10,5,3,8,9};
        
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        System.out.println(solution.lengthOfLIS(nums));
    }
}
