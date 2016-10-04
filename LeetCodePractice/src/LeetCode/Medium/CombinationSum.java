package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 * 
 * A solution set is:
 * 
 * [
 *   [7],
 *   [2, 2, 3]
 *   
 * ]
 * 
 * @author WinnieZhao
 *
 */
public class CombinationSum {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result  = new ArrayList<List<Integer>>();
        this.combinationSumHelper(candidates, target, result, new ArrayList<Integer>(), 0);
        
        return result;
    }
    
    private void combinationSumHelper(int[] candidates, int target, List<List<Integer>> result, List<Integer> combination, int start) {
        
        if (target < 0) {
            return;
        }
        else if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
        }
        else {
            for (int i=start; i<candidates.length; i++) {
                combination.add(candidates[i]);
                this.combinationSumHelper(candidates, target - candidates[i], result, combination, i);
                combination.remove(combination.size()-1);
            }
        }
    }
    
    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        
        int[] candidates = {2, 2, 3, 7};

        List<List<Integer>> result = solution.combinationSum(candidates, 7);
        
        for (List<Integer> combinantion : result) {
            System.out.println(combinantion.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }
    }
}
