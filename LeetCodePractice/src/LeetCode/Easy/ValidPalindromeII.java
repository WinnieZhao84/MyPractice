package LeetCode.Easy;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 *
 * Explanation: You could delete the character 'c'.
 *
 * Note: The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

 * Created by WinnieZhao on 9/17/2017.
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() <=1) {
            return true;
        }

        for (int i=0; i<s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                int j = s.length() - 1 - i;
                return this.inValidRange(s, i, j-1) || this.inValidRange(s, i+1, j);
            }
        }

        return true;
    }

    private boolean inValidRange(String s, int i, int j) {
        for (int k=i; k<=i+(j-i)/2; k++) {
            if (s.charAt(k) != s.charAt(j-k+i)) {
                return false;
            }
        }

        return true;
    }
}
