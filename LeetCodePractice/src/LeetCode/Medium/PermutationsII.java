package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * For example,
 * [1,1,2] have the following unique permutations:
 * 
 * [
 *  [1,1,2],
 *  [1,2,1],
 *  [2,1,1]
 * ]
 * 
 * 
 * @author WinnieZhao
 *
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(nums==null || nums.length==0) return result;
        
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        
        this.permuteUnique_helper(result, new ArrayList<>(), nums, used);
        
        return result;
    }
    
    private void permuteUnique_helper(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] used) {
        
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (used[i]) continue;
            if (i>0 && nums[i-1]==nums[i] && !used[i-1]) continue;
            
            list.add(nums[i]);
            used[i] = true;

            this.permuteUnique_helper(result, list, nums, used);
            
            list.remove(list.size()-1);
            used[i] = false;
        }
    }
    
    public static void main(String[] args) {
        PermutationsII solution = new PermutationsII();
        
        int[] nums = {1,1,2};
        System.out.println(solution.permuteUnique(nums).toString());
    }
    
}
