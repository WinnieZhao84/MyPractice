package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
 * that add up to a positive integer target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * 
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * Follow up:
 * 
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * 
 * @author WinnieZhao
 *
 */
public class CombinationSumIV {
    Map<Integer, Integer> map = new HashMap<>();
    
    public int combinationSum4(int[] nums, int target) {
        
        int result = this.combinationSumsHelper(target, nums);
        
        return result;
    }
    

    private int combinationSumsHelper(int target, int[] nums) {
        int count = 0;
        if (target < 0) {
            return 0;
        }
        else if (target == 0) {
            return 1;
        }
        // Time Exceed Limitation if without this map to store intermediate data
        else if (map.containsKey(target)) {
            return map.get(target);
        }
        else {
            for (int i=0; i<nums.length; i++) {
                count += this.combinationSumsHelper(target-nums[i], nums);
            }
            map.put(target, count);
            return count;
        }
    }

    /**
     * DP: Bottom up
     */
    public int combinationSum4_DP(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] combinations = new int[target+1];
        combinations[0] = 1;

        for (int i=1; i<=target; i++) {
            for (int j=0; j<nums.length; j++) {
                if (i >= nums[j]) {
                    combinations[i] += combinations[i - nums[j]];
                }
            }
        }

        return combinations[target];
    }

    /**
     * For the Follow up:
     *
     * If there are negative numbers in the array, we must add a requirement that each number is only used one time,
     * or either positive number or negative number should be used only one time, otherwise there would be infinite
     * possible combinations.
     */
    
    public static void main(String[] args) {
        CombinationSumIV solution = new CombinationSumIV();
        int[] nums = {1, 2, 3};
        
        System.out.println(solution.combinationSum4(nums, 4));
    }
}
