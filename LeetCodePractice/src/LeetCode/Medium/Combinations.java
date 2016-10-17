package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example,
 * 
 * If n = 4 and k = 2, a solution is:
 * 
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 * 
 * @author WinnieZhao
 *
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (n<=0 || k<=0 || n<k) {
            return result;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        
        this.combineHelper(result, list, 1, n, k);
        
        return result;
    }
    
    private void combineHelper(List<List<Integer>> result, List<Integer> list, int start, int n, int k) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i=start; i<=n; i++) {
            if (list.contains(i)) {
                continue;
            }
            list.add(i);
            
            this.combineHelper(result, list, i+1, n, k);
            
            list.remove(list.size() -1);
            
        }
    }
    
    public static void main(String[] args) {
        Combinations solution = new Combinations();
        
        List<List<Integer>> result = solution.combine(4, 2);
        
        System.out.println(result);
    }
}
