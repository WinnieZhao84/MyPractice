package LeetCode.Interview.Amazon.LevelOne;

/**
 * Same as leetcode
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String max = s.substring(0, 1);
        for (int i=0; i<s.length(); i++) {
            String temp = this.getPalindrome(s, i, i);
            if (temp.length() > max.length()) {
                max = temp;
            }

            temp = this.getPalindrome(s, i, i+1);
            if (temp.length() > max.length()) {
                max = temp;
            }
        }

        return max;
    }

    private String getPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        return s.substring(start+1, end);
    }
}
