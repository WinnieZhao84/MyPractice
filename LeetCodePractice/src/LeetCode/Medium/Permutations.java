package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * For example,
 * [1,2,3] have the following permutations
 * 
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 * 
 * @author WinnieZhao
 *
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        this.permute_helper(result, new ArrayList<>(), nums);
        
        return result;
    }
    
    private void permute_helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            this.permute_helper(result, list, nums);
            list.remove(list.size()-1);
        }
    }
    
    public static void main(String[] args) {
        Permutations solution = new Permutations();
        
        int[] nums = {1,2,3,4};
        System.out.println(solution.permute(nums).size());
    }
}
