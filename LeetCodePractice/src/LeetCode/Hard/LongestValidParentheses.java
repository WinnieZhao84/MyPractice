package LeetCode.Hard;

import java.util.Stack;

/**
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 * Created by WinnieZhao on 2017/4/27.
 */
public class LongestValidParentheses {

    /**
     * The char that makes the string invalid parentheses will cut the string into parts that either is valid parentheses or empty.
     * Use a stack to process the string, then the stack is empty, while met ')', calculate the size.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int length = s.length();
        Stack<Integer> stack = new Stack<>();

        if (s.indexOf(')') < 0 || s.indexOf('(') < 0) {
            return 0;
        }
        int invalidPos = -1;
        int start = 0;
        int max = 0;
        for (int i=0; i<length; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            }
            else {
                if (!stack.isEmpty()) {
                    stack.pop();

                    start = stack.isEmpty() ? invalidPos : stack.peek();
                    max = Math.max(i - start, max);
                }
                else {
                    invalidPos = i;
                }

            }

        }

        return max;
    }
}
