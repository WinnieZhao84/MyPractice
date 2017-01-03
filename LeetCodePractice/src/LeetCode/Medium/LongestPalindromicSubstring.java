package LeetCode.Medium;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 * 
 * @author WinnieZhao
 *
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        
        String longest = s.substring(0,1);
        for (int i=0; i<s.length(); i++) {
            String temp = this.getPalindrome(s, i, i);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
            
            temp = this.getPalindrome(s, i, i+1);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
        }
        
        return longest;
    }
    
    private String getPalindrome(String s, int begin, int end) {
        while (begin>=0 && end<=s.length()-1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        
        return s.substring(begin+1, end);
    }
    
    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        
        System.out.println(solution.longestPalindrome("babad"));
    }
}
