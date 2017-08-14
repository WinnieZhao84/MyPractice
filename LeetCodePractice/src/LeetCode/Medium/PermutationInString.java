package LeetCode.Medium;

/**
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 *
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 *
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].

 * Created by WinnieZhao on 2017/5/1.
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s2 == null) return s1 == null ? true : false;
        if (s2.isEmpty()) return s1.isEmpty() ? true : false;
        if (s2.length() < s1.length()) return false;

        int l1 = s1.length();
        int l2 = s2.length();

        int[] s1map = new int[26];
        for (int i=0; i< l1; i++) {
            s1map[s1.charAt(i) - 'a']++;
        }

        for (int i=0; i<=l2-l1; i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < l1; j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map))
                return true;
        }

        return false;
    }

    // Slide window
    // Time complexity: O(l​1 ​​+ 26∗(l2 ​​− l​1)), where l1 is the length of string s1 and l2 is the length of string s2
    // Space complexity: O(1). Constant space is used.
    public boolean checkInclusion_better(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)) {
                return true;
            }
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}
