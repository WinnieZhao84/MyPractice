package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,....,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the following sequence (ie, for n = 3):
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * Given n and k, return the kth permutation sequence. Note: Given n will be between 1 and 9 inclusive.
 * 
 * @author WinnieZhao
 *
 */
public class PermutationSequence {

    public String getPermutation(int n, int k) {
        List<String> result = new ArrayList<String>();
        
        this.getPermutationHelper(n, result, "");
        
        String res = result.get(k-1);
        
        return res;
    }
    
    // Time Limit Exceeded
    private void getPermutationHelper(int n, List<String> result, String s) {
        if (s.length() == n) {
            result.add(s);
        }
        
        for (int i=1; i<=n; i++) {
            if (s.contains(String.valueOf(i))) {
                continue;
            }
            
            s = s + String.valueOf(i);
            this.getPermutationHelper(n, result, s);
            s = s.substring(0, s.length()-1);
        }
    }
    
    public String getPermutation_better(int n, int k) {

        int mod = 1;
        List<Integer> candidates = new ArrayList<>();
        // 先得到n!和候选数字列表
        for(int i = 1; i <= n; i++){
            mod = mod * i;
            candidates.add(i);
        }
        // 将k先减1方便整除
        k--;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n ; i++){
            mod = mod / (n - i);
            // 得到当前应选数字的序数
            int first = k / mod;
            // 得到用于计算下一位的k
            k = k % mod;
            sb.append(candidates.get(first));
            // 在列表中移出该数字
            candidates.remove(first);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        
        System.out.println(solution.getPermutation_better(4, 3));
    }
}
