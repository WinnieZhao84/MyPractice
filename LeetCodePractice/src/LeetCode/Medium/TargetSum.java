package LeetCode.Medium;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. 
 * For each integer, you should choose one from + and - as its new symbol.
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * 
 * Explanation: 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.

 * @author WinnieZhao
 *
 */
public class TargetSum {
    
    int count = 0; // For DFS

    public int findTargetSumWays_dp(int[] nums, int S) {
        if ((nums == null || nums.length == 0) && S == 0) return 0;
        
        int length = nums.length;
        int[] dp = new int[length+1];
        dp[0] = 0;
        
        return 0;
        
        
    }
    
    public int findTargetSumWays_dfs(int[] nums, int S) {
        if ((nums == null || nums.length == 0) && S == 0) return 0;
        
        this.dfs(nums, S, 0, 0);
        
        return count;
    }
    
    private void dfs(int nums[], int target, int sum, int index) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }

        this.dfs(nums, target, sum+nums[index], index+1);
        this.dfs(nums, target, sum-nums[index], index+1);
    }
    
    /**
     * Optimization: The idea is If the sum of all elements left is smaller than absolute value of target, 
     * there will be no answer following the current path. Thus we can return.
     * 
     * @param args
     */
    
    public int findTargetSumWays_dfs_better(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            sums[i] = sums[i + 1] + nums[i];
        
        helper(nums, sums, S, 0);
        return count;
    }
    private void helper(int[] nums, int[] sums, int target, int pos){
        if(pos == nums.length){
            if(target == 0) count++;
            return;
        }
        
        if (sums[pos] < Math.abs(target)) return;
        
        helper(nums, sums, target + nums[pos], pos + 1);
        helper(nums, sums, target - nums[pos], pos + 1);
    }
    
    public static void main(String[] args) {
        TargetSum solution = new TargetSum();
        
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(solution.findTargetSumWays_dfs(nums, 3));
    }
}
