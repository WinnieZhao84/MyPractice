package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: 
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. 
 * In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 * 
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 * Example 1:
 * Input: "a"
 * Output: 1
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * 
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * 
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 * 
 * @author WinnieZhao
 *
 */
public class UniqueSubstringsInWraparoundString {
    
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.isEmpty()) {
            return 0;
        }
        
        int len = p.length();
        if (len == 1) return 1;
        
        int[] dp = new int[26];
        int maxLengthCur = 0;
        for (int i=0; i<len; i++) {
            if (i>0 && (p.charAt(i) - p.charAt(i-1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
                maxLengthCur++;
            }
            else {
                maxLengthCur = 1;
            }
            int index = p.charAt(i) - 'a';
            dp[index] = Math.max(maxLengthCur, dp[index]);
        }
        
        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += dp[i];
        }
        return sum;
    }
    
    public static void main(String[] args) {
        UniqueSubstringsInWraparoundString solution = new UniqueSubstringsInWraparoundString();
        System.out.println(solution.findSubstringInWraproundString("abcdzabc"));
    }
}
