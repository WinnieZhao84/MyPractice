package LeetCode.Hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 854. K-Similar Strings
 * 
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 * 
 * Example 1:
 * 
 * Input: A = "ab", B = "ba"
 * Output: 1
 * 
 * Example 2:
 * Input: A = "abc", B = "bca"
 * Output: 2
 * 
 * Example 3:
 * Input: A = "abac", B = "baca"
 * Output: 2
 * 
 * Example 4:
 * Input: A = "aabc", B = "abca"
 * Output: 2
 * 
 * Note:
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

 * @author WinnieZhao
 *
 */
public class KSimilarStrings {
    
    /**
     * Each meaningful swap must match at least one character of A to B
     * The order of swaps doesn't matter
     * Already matched characters should NOT be swapped, since if removing all the matched characters, the resulting two anagrams should give same k-similar.
     */
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.add(A);
        
        Set<String> visited = new HashSet<>();
        visited.add(A);
        
        int steps = 0;
        while(!queue.isEmpty()) {
            
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                
                String cur = queue.poll();
                
                if (cur.equals(B)) {
                    return steps;
                }
                
                int j=0;
                
                while (j<cur.length() && cur.charAt(j) == B.charAt(j)) {
                    j++;
                }
                
                for (int k=j+1; k<cur.length(); k++) {
                    if (cur.charAt(k) == B.charAt(k) || cur.charAt(k) != B.charAt(j) ) {
                        continue;
                    }
                    String next = swap(cur, j, k);
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
    
    
    private String swap(String s, int i, int j) {
        char[] chs = s.toCharArray();
        
        char ch = chs[i];
        chs[i] = chs[j];
        chs[j] = ch;
        
        return String.valueOf(chs);
    }

}
