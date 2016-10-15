package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,бн,n] contains a total of n! unique permutations.
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
        
        // Get n!, the possibilities of the permutation of n and put it to the array
        int sum = 1;
        int[] factors = new int[n];
        factors[0] = 1;
        for (int i=1; i<n; i++) {
            sum = sum * i;
            factors[i] = sum;
        }
        
        StringBuilder builder = new StringBuilder();
        
        k = k-1;
        
        // Create a list of numbers to get indices
        List<Integer> numbers = new ArrayList<Integer>();
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        
        for (int i=1; i<=n; i++) {
            int index = k / factors[n-i];
            Integer letter = numbers.get(index);
            builder.append(letter);
            
            numbers.remove(index);
            k = k - (index * factors[n-i]);
        }
        
        return builder.toString();
    }
    
    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        
        System.out.println(solution.getPermutation_better(4, 3));
    }
}
