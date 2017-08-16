package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 
 * @author WinnieZhao
 *
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        result.add(new ArrayList<>());
        this.subsetsHelper(result, new ArrayList<>(), nums, 0);
        
        return result;
    }
    
    private void subsetsHelper(List<List<Integer>> result, List<Integer> list, int nums[], int start) {
        
        for (int i=start; i<nums.length; i++) {
            if (i>start && nums[i-1] == nums[i]) {
                continue;
            }
            
            list.add(nums[i]);
            
            result.add(new ArrayList<>(list));
            
            this.subsetsHelper(result, list, nums, i+1);
            
            list.remove(list.size() -1);
        }
    }
    
    public static void main(String[] args) {
        SubsetsII solution = new SubsetsII();
        
        int[] nums = {1,2,2};
        List<List<Integer>> result = solution.subsetsWithDup(nums);
        
        System.out.println(result);
    }
}
