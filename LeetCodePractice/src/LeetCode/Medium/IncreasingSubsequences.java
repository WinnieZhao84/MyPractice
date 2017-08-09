package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
 * and the length of an increasing subsequence should be at least 2 .
 * 
 * Example:
 * 
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

 * @author WinnieZhao
 *
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        this.dfs(result, nums, new ArrayList<Integer>(), 0);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, int[] nums, List<Integer> res, int index) {
        if (res.size() >= 2) {
            result.add(new ArrayList<Integer>(res));
        }
        
        List<Integer> unique = new ArrayList<Integer>();
        for (int i = index; i<nums.length; i++) {
            if (res.size() > 0 && nums[i] < res.get(res.size()-1)) {
                continue; // skip non-increase
            }

            if (unique.contains(nums[i])) {
                continue; // skip duplicate
            }
            unique.add(nums[i]);
            
            res.add(nums[i]);
            this.dfs(result, nums, res, i + 1);
            res.remove(res.size()-1);

        }
    }
    
    public static void main(String[] args) {
        IncreasingSubsequences solution = new IncreasingSubsequences();
        
        int[] nums = {4,6,7,7};
        
        solution.findSubsequences(nums).forEach(l -> System.out.println(l));

    }
    
}
