package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * A solution set is: 
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * @author WinnieZhao
 *
 */
public class CombinationSumII {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Arrays.sort(candidates);
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
                if(i > start && candidates[i] == candidates[i-1]) {
                    continue; // skip duplicates
                }
                
                combination.add(candidates[i]);
                this.combinationSumHelper(candidates, target - candidates[i], result, combination, i+1);
                combination.remove(combination.size()-1);
            }
        }
    }
    
    public static void main(String[] args) {
        CombinationSumII solution = new CombinationSumII();
        
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};

        List<List<Integer>> result = solution.combinationSum2(candidates, 8);
        
        for (List<Integer> combinantion : result) {
            System.out.println(combinantion.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }
    }
}
