package LeetCode.Medium;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 * 
 * Example 1:
 * 
 * Input:
 * s = "aaabb", k = 3
 * Output: 3
 * The longest substring is "aaa", as 'a' is repeated 3 times
 * 
 * Example 2:
 * Input:
 * s = "ababbc", k = 2
 * Output:
 * 5
 * 
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

 * @author WinnieZhao
 *
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    /**
    public int longestSubstring_better(String s, int k) {
        int res = 0, i = 0, n = s.size();
        while (i + k < n) {
            int m[26] = {0}, mask = 0, max_idx = i;
            for (int j = i; j < n; ++j) {
                int t = s[j] - 'a';
                ++m[t];
                if (m[t] < k) mask |= (1 << t);
                else mask &= (~(1 << t));
                if (mask == 0) {
                    res = max(res, j - i + 1);
                    max_idx = j;
                }
            }
            i = max_idx + 1;
        }
        return res;
    }
    **/

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k) return 0;
        if (k == 0)  return s.length();
        
        return this.dc(s, 0, s.length(), k);
    }
    
    // Divide and conquer
    private int dc(String s, int start, int end, int k) {
        if (end - start < k) return 0;
        
        int[] chars = new int[26];
        // record the frequency of each character
        for (int i = start; i < end; i++) {
            chars[s.charAt(i) - 'a']++;
        }
        
        for (int i = start; i < end; i++) {
            if (chars[s.charAt(i) - 'a'] < k) {
                return Math.max(dc(s, start, i, k), dc(s, i + 1, end, k));
            }
        }
        return end - start;
    }
    
    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters solution = new LongestSubstringWithAtLeastKRepeatingCharacters();
        
        System.out.println(solution.longestSubstring("ababacb", 2));
    }
    
}
