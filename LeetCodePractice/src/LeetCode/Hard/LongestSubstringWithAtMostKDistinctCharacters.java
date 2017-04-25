package LeetCode.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 340
 *
 * Given a string, find the length of the longest substring T that contains at most K distinct characters
 *
 * Created by WinnieZhao on 4/24/2017.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int start = 0;
        int maxLen = 0;

        // Key: letter; value: the number of occurrences.
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i;
        for (i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
                while (map.size() > k) {
                    char startChar = s.charAt(start++);
                    int count = map.get(startChar);
                    if (count > 1) {
                        map.put(startChar, count - 1);
                    } else {
                        map.remove(startChar);
                    }
                }
            }

            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }
}
