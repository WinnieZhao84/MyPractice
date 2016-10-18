package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example:
 * If nums = [1,2,3], a solution is:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 **/
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        result.add(new ArrayList<>());
        this.subsetsHelper(result, new ArrayList<>(), nums, 0);
        
        return result;
    }
    
    private void subsetsHelper(List<List<Integer>> result, List<Integer> list, int nums[], int start) {
        
        for (int i=start; i<nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            
            this.subsetsHelper(result, list, nums, i+1);
            
            list.remove(list.size() -1);
        }
    }
    
    public static void main(String[] args) {
        Subsets solution = new Subsets();
        
        int[] nums = {1,2,3};
        List<List<Integer>> result = solution.subsets(nums);
        
        System.out.println(result);
    }
}
