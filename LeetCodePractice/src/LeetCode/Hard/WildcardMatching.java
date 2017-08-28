package LeetCode.Hard;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 *
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false

 * Created by WinnieZhao on 2017/6/19.
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {

        if ((s == null && s == p) || s.equals(p)) {
            return true;
        }

        int sPointer = 0;
        int pPointer = 0;
        int starIndex = -1;
        int mark = -1;
        while (sPointer<s.length()) {
            // both pointers ++
            if (pPointer < p.length()  && (p.charAt(pPointer) == '?' || s.charAt(sPointer) == p.charAt(pPointer))) {
                sPointer++;
                pPointer++;
            }
            // * found, only ++ pattern pointer
            else if (pPointer < p.length() && p.charAt(pPointer) == '*') {
                starIndex = pPointer;
                pPointer++;
                mark = sPointer;
            }
            // last pattern pointer was *, string pointer ++
            // Try to match the current index from String s with the next char after the last "*" from Pattern p
            // if it's matched, covered from the first if; otherwise compare the next char from String s
            else if (starIndex != -1){
                pPointer = starIndex + 1;
                mark++;
                sPointer = mark;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else {
                return false;
            }
        }
        while (pPointer < p.length() && p.charAt(pPointer) == '*') {
            pPointer++;
        }
        return pPointer == p.length();
    }

    public static void main(String[] args) {
        WildcardMatching solution = new WildcardMatching();

        System.out.println(solution.isMatch("aaa", "*"));
    }

}
