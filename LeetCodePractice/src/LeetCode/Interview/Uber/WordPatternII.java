package LeetCode.Interview.Uber;

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

    public boolean wordPatternMatch(String pattern, String str) {

        return this.isMatch(pattern, 0, str, 0, new HashMap<>(), new HashSet<>());
    }

    private boolean isMatch(String pattern, int pIndex, String str, int sIndex, Map<Character, String> map, Set<String> visited) {

        if (pIndex == pattern.length() && sIndex == str.length()) {
            return true;
        }
        else if (pIndex == pattern.length() || sIndex == str.length()) {
            return false;
        }

        char ch = pattern.charAt(pIndex);

        if (map.containsKey(ch)) {
            String s = map.get(ch);

            if (!str.startsWith(s, sIndex)) {
                return false;
            }
            else {
                return this.isMatch(pattern, pIndex+1, str, sIndex+s.length(), map, visited);
            }
        }
        else {
            for (int i=sIndex; i<str.length(); i++) {
                String s = str.substring(sIndex, i+1);

                if (visited.contains(s)) {
                    continue;
                }

                visited.add(s);
                map.put(ch, s);

                if (this.isMatch(pattern, pIndex+1, str, sIndex+s.length(), map, visited)) {
                    return true;
                }

                map.remove(ch);
                visited.remove(s);
            }

            return false;
        }
    }

    public static void main(String[] args) {
        WordPatternII solution = new WordPatternII();

        System.out.println(solution.wordPatternMatch("abab", "redblueredblue"));
        System.out.println(solution.wordPatternMatch("aaaa", "asdasdasdasd"));
    }
}
