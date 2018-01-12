package LeetCode.Easy;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid. 
 * The brackets must close in the correct order, 
 * "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * @author ASUS-PC
 *
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        char[] chs = s.toCharArray();
        int len = chs.length;

        Stack<Character> stack = new Stack<>();
        for (int i=0; i<len; i++) {
            if (chs[i] == '{' || chs[i] == '[' || chs[i] == '(') {
                stack.push(chs[i]);
            }
            else if (!stack.isEmpty() && (
                    (stack.peek() == '(' && chs[i] == ')') ||
                            (stack.peek() == '{' && chs[i] == '}') ||
                            (stack.peek() == '[' && chs[i] == ']'))) {
                stack.pop();
            }
            else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        System.out.print(solution.isValid("()]{}"));
    }
}
