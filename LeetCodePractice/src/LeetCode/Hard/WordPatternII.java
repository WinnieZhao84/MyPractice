package LeetCode.Hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 291
 *
 * Problem Description:
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 *
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 *
 * Created by WinnieZhao on 4/25/2017.
 */
public class WordPatternII {

    /**
     *  using backtracking, we just have to keep trying to use a character in the pattern to match different length of
     *  substrings in the input string, keep trying till we go through the input string and the pattern.
     *  For example, input string is "redblueredblue", and the pattern is "abab", first let's use 'a' to match "r",
     *  'b' to match "e", then we find that 'a' does not match "d", so we do backtracking, use 'b' to match "ed",
     *  so on and so forth ...
     *
     *  When we do the recursion, if the pattern character exists in the hash map already, we just have to see
     *  if we can use it to match the same length of the string. For example, let's say we have the following map:
     *  'a': "red"
     *  'b': "blue"
     *  now when we see 'a' again, we know that it should match "red", the length is 3, then let's see if str[i ... i+3]
     *  matches 'a', where i is the current index of the input string. In Java we can elegantly use str.startsWith(s, i)
     *  to do the check.
     *  Also we can use a hash set to avoid duplicate matches, if character a matches string "red", then character b
     *  cannot be used to match "red"
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

    public static void main(String[] args) {
        WordPatternII solution = new WordPatternII();

        System.out.println(solution.wordPatternMatch("abab", "redblueredblue"));
    }

}
