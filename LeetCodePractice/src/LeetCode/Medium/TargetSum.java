package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

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
 *
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 */
public class TargetSum {

    /**
     * Solution 1:
     * Time complexity : O(2^n). Size of recursion tree will be 2^n. n refers to the size of nums array.
     * Space complexity : O(n). The depth of the recursion tree can go upto n.
     */
    int count = 0; // For DFS
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
     * Solution 2:
     * DFS with memoization: It can be easily observed that in the last approach, a lot of redundant function calls
     * could be made with the same value of i as the current index and the same value of sum as the current sum,
     * since the same values could be obtained through multiple paths in the recursion tree. In order to remove
     * this redundancy, we make use of memoization as well to store the results which have been calculated earlier.
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        return helper(nums, 0, 0, S, new HashMap<>());
    }

    private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map){
        String encodeString = index + "->" + sum;
        if (map.containsKey(encodeString)){
            return map.get(encodeString);
        }
        if (index == nums.length){
            if (sum == S){
                return 1;
            }
            return 0;
        }

        int curNum = nums[index];
        int add = helper(nums, index + 1, sum - curNum, S, map);
        int minus = helper(nums, index + 1, sum + curNum, S, map);

        map.put(encodeString, add + minus);

        return add + minus;
    }

    /**
     * Solution 3:
     * Optimization: The idea is If the sum of all elements left is smaller than absolute value of target, 
     * there will be no answer following the current path. Thus we can return.
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

    /**
     * Same as DFS solution 1, difference is not using general variable
     */
    class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int sum = this.dfs(nums, S, 0, 0);

            return sum;
        }

        private int dfs(int[] nums, int target, int sum, int pos) {

            int res = 0;
            if (nums.length == pos) {
                if (target == sum) {
                    return 1;
                }
                return 0;
            }

            res += dfs(nums, target, sum + nums[pos], pos+1);
            res += dfs(nums, target, sum - nums[pos], pos+1);

            return res;
        }
    }

    public static void main(String[] args) {
        TargetSum solution = new TargetSum();
        
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(solution.findTargetSumWays_dfs_better(nums, 3));
    }
}
