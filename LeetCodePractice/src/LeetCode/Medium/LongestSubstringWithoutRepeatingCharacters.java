package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
 * "pwke" is a subsequence and not a substring.

 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     *
     * Sliding Window: Define a mapping of the characters to its index. Then we can skip the characters
     * immediately when we found a repeated character.
     *
     * The reason is that if s[j] have a duplicate in the range [i, j) with index j',
     * we don't need to increase i little by little. We can skip all the elements in the range [i, j']
     * and let i to be j' + 1 directly.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return s.length();
        }

        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i=0, k=0; i<length; i++) {
            if (map.containsKey(s.charAt(i))) {
                k = Math.max(k, map.get(s.charAt(i)));
            }

            map.put(s.charAt(i), i+1);
            res = Math.max(res, i-k+1);
        }

        return res;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("au"));
    }
}
