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

    public int lengthOfLIS(int[] nums) {
        
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) {
                len++;
            }
        }

        return len;
    }
    
    public int lengthOfLIS_DP(int[] nums) {
        
        if (nums == null || nums.length <= 1) return nums.length;
        
        int[] dp = new int[nums.length];

        int max = 1;
        for(int i=0; i<nums.length;i++){
            dp[i]=1;
            
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] nums = {4,10,4,3,8,9};
        
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        System.out.println(solution.lengthOfLIS(nums));
    }
}
