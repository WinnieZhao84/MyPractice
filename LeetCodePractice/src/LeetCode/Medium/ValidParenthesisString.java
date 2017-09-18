package LeetCode.Medium;

import java.util.Stack;

/**
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid.
 *
 * We define the validity of a string by these rules:
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 *
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 *
 * Example 2:
 * Input: "(*)"
 * Output: True
 *
 * Example 3:
 * Input: "(*))"
 * Output: True

 * Created by WinnieZhao on 9/17/2017.
 */
public class ValidParenthesisString {

    /**
     * low : take '*' as ')', if there are some '(' not matched
     * high : take '*' as '('
     *
     * if high < 0 means too much ')'
     * if low > 0 , after the count finished, means too much '('

     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int length = s.length();

        int low=0;
        int high=0;
        for (int i=0; i<length; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                low++;
                high++;
            }
            else if (ch == ')') {
                if (low>0) {
                    low--;
                }
                high--;
            }
            else {
                if (low>0) {
                    low--;
                }
                high++;
            }

            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }

    public static void main(String[] args) {
        String s = "(***)";
        ValidParenthesisString solution = new ValidParenthesisString();
        System.out.println(solution.checkValidString(s));
    }

}
