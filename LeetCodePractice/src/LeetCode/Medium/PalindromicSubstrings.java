package LeetCode.Medium;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 *
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note:
 * The input string length won't exceed 1000.

 * Created by WinnieZhao on 2017/7/23.
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {

        if (s == null) {
            return 0;
        }

        int length = s.length();
        if (length < 2) {
            return length;
        }

        int count = 0;
        for (int i=0; i<length; i++) {
            for (int j=length-1; j>=i; j--) {
                if (isPalindromic(s, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }


    private boolean isPalindromic(String s, int start, int end) {
        if (s == null) {
            return false;
        }

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromicSubstrings solution = new PalindromicSubstrings();

        System.out.println(solution.countSubstrings("aaa"));
        System.out.println(solution.countSubstrings("abc"));
    }
}
