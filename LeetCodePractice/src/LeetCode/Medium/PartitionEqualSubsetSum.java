package LeetCode.Medium;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * 
 * Note: 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * @author WinnieZhao
 *
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % 2 != 0) {
            return false;
        }
        
        sum = sum/2;
        boolean[][] dp = new boolean[sum+1][nums.length+1];
        dp[0][0]=true;

        for (int i=0; i<=sum; i++) {
            for (int j=0; j<nums.length; j++) {
               dp[i][j+1] = dp[i][j] || ( i>=nums[j] ? dp[i-nums[j]][j] : false);
            }
        }
        
        return dp[sum][nums.length];
    }
}
