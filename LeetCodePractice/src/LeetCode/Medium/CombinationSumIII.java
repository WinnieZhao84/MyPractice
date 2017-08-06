package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used
 * and each combination should be a unique set of numbers.
 * 
 * Example 1:
 * 
 * Input: k = 3, n = 7
 * 
 * Output: [[1,2,4]]
 * 
 * Example 2:
 * 
 * Input: k = 3, n = 9
 * 
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * @author WinnieZhao
 *
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        this.combinationSumHelper(k, n, result, new ArrayList<>(), 1);
        
        return result;
    }
    
    private void combinationSumHelper (int count, int target, List<List<Integer>> result, List<Integer> combination, int start) {
        
        if (target < 0 && combination.size() == count) {
            return;
        }
        else if (target == 0 && combination.size() == count) {
            result.add(new ArrayList<>(combination));
        }
        else {
            for (int i=start; i<=9; i++) {
                combination.add(i);
                this.combinationSumHelper(count, target-i, result, combination, i+1);
                combination.remove(combination.size()-1);
            }
        }

    }
    
    public static void main(String[] args) {
        CombinationSumIII solution = new CombinationSumIII();

        List<List<Integer>> result = solution.combinationSum3(3, 9);
        
        for (List<Integer> combination : result) {
            System.out.println(combination.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }
    }
}
