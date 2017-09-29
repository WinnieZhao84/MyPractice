package LeetCode.Interview.Amazon;

import java.util.Stack;

/**
 * Created by WinnieZhao on 9/28/2017.
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        if (s == null || s.length() <=1 ){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i=0; i<s.length();i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else {
                if (stack.isEmpty() && (ch == ')' || ch == ']' || ch == '}')) {
                    return false;
                }
                char leftParentheses = stack.peek();
                if ((ch == ')' && leftParentheses == '(') ||
                        (ch == '}' && leftParentheses == '{') ||
                        (ch == ']' && leftParentheses == '[')) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
