package LeetCode.Interview.Microsoft.LeetCode;

/**
 * 186
 *
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Could you do it in-place without allocating extra space?

 */
public class ReverseWordsInAStringII {

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length-1);  // reverse the whole string first
        int r = 0;
        while (r < s.length) {
            int l = r;
            while (r < s.length && s[r] != ' ')
                r++;
            reverse(s, l, r-1);  // reverse words one by one
            r++;
        }
    }

    private void reverse(char[] s, int l, int r) {
        while (l < r) {
            char tmp = s[l];
            s[l++] = s[r];
            s[r--] = tmp;
        }
    }

}
