package LeetCode.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 159
 *
 *
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 *
 * Created by WinnieZhao on 4/24/2017.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {

        int start = 0;
        int maxLen = 0;

        // Key: letter; value: the index of the last occurrence.
        Map<Character, Integer> map = new HashMap<>();
        int i;

        for (i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (map.size() == 2 && !map.containsKey(c)) {
                // Pick the character with the leftmost last occurrence.
                char charEndsMostLeft = ' ';
                int minLast = s.length();
                for (char ch : map.keySet()) {
                    int last = map.get(ch);
                    if (last < minLast) {
                        minLast = last;
                        charEndsMostLeft = ch;
                    }
                }

                map.remove(charEndsMostLeft);
                start = minLast + 1;
            }
            map.put(c, i);
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }
}
