package LeetCode.Interview.Microsoft.OA;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given a string abababcdbssaaabbbbb. Find the longest sub string of K consecutive characters
 * E.g. if k=2, consecutive sub string is;
 *  ababab : 6
 *  aaabbbbb : 8
 *
 *  so longest answer is 8.
 */
public class LongestConsecutiveSubString {

    public int getLongestConsecutiveSubString(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k) {
            return 0;
        }

        int len = s.length();

        int left = 0;
        int right = 1;

        TreeMap<Character, List<Integer>> map = new TreeMap<>();
        List<Integer> index = new ArrayList<>();
        index.add(left);

        map.put(s.charAt(left), index);

        int max = 1;
        while (right < len) {
            char key = s.charAt(right);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(right);

            char low = map.firstKey();
            char high = map.lastKey();
            int cnt = high - low + 1;

            if (cnt <= k) {
                right++;

                max = Math.max(max, right - left);
                continue;
            }

            while (cnt > k) {
                map.get(s.charAt(left)).remove(0);
                if (map.get(s.charAt(left)).size() == 0) {
                    map.remove(s.charAt(left));
                }

                left++;

                low = map.firstKey();
                high = map.lastKey();
                cnt = high - low + 1;
            }
            right++;
        }

        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSubString solution = new LongestConsecutiveSubString();

        System.out.println(solution.getLongestConsecutiveSubString("abababcdbssaaabbbbb", 2));
        System.out.println(solution.getLongestConsecutiveSubString("abababcbcbcb", 2));
        System.out.println(solution.getLongestConsecutiveSubString("abcdef", 4));
    }
}
