package LeetCode.Hard;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 *
 * Minimum window is "BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Created by WinnieZhao on 2017/4/26.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        if (s.length() < t.length()) return "";
        if (s.length() == t.length() && s.equals(t)) return t;

        /**
         * Generally, there are following steps:
         *
         * 1. Create a hashmap for each character in t and count their frequency in t as the value of hashmap.
         * 2. Find the first window in S that contains T. But how? there the author uses the count.
         * 3. Checking from the leftmost index of the window and to see if it belongs to t. The reason we do so is that we want to shrink the size of the window.
         *   3-1) If the character at leftmost index does not belong to t, we can directly remove this leftmost value and update our window(its minLeft and minLen value)
         *   3-2) If the character indeed exists in t, we still remove it, but in the next step, we will increase the right pointer and expect the removed character.
         *   If find so, repeat step 3.
         */
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int minLeft = 0;
        int minLen = Integer.MAX_VALUE;
        int count = 0;

        for (int right = 0; right<s.length(); right++) {
            char r = s.charAt(right);

            if (map.containsKey(r)){ //the goal of this part is to get the first window that contains whole t
                map.put(r, map.get(r)-1);

                if (map.get(r) >= 0) count++; //identify if the first window is found by counting frequency of the characters of t showing up in S
            }

            // if the count is equal to the length of t, then we find such window
            while(count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // starting from the leftmost index of the window, we want to check if s[left] is in t.
                // If so, we will remove it from the window, and increase 1 time on its counter in hashmap
                // which means we will expect the same character later by shifting right index.
                // At the same time, we need to reduce the size of the window due to the removal.
                char l = s.charAt(left);
                if(map.containsKey(l)){
                    map.put(l, map.get(l)+1);
                    if(map.get(l)>0) count--;
                }
                left++;//if it doesn't exist in t, it is not supposed to be in the window, left++. If it does exist in t, the reason is stated as above. left++.
            }
        }

        return minLen==Integer.MAX_VALUE ? "": s.substring(minLeft, minLeft+minLen);
    }

    public String minWindow_better(String s, String t) {
        if (s == null || s.isEmpty() || t == null || s.length() < t.length()) {
            return "";
        }

        if (s.equals(t)) {
            return s;
        }

        int[] map = new int[128];
        for (char ch : t.toCharArray()) {
            map[ch]++;
        }

        int start=0, end=0;
        int count = t.length();

        int minStart = 0;
        int minLength = Integer.MAX_VALUE;

        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0) {
                count--;
            }

            while (count == 0) {
                int len = end - start;
                if (len < minLength) {
                    minStart = start;
                    minLength = len;
                }

                if (map[s.charAt(start++)]++ == 0) {
                    count++;
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minLength + minStart);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        System.out.println(solution.minWindow_better("ADOBECODEBANC", "ABC"));
    }
}
