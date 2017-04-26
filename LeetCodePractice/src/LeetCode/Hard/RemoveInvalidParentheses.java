package LeetCode.Hard;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 *
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]

 * Created by WinnieZhao on 4/26/2017.
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0) return result;

        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        Set<String> visited = new HashSet<>();

        boolean found = false;
        while(!queue.isEmpty()) {
            s = queue.poll();

            if (this.isValid(s)) {
                result.add(s);
                found = true;
            }

            if (found) continue;

            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String newStr = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(newStr)) {
                    queue.add(newStr);
                    visited.add(newStr);
                }
            }
        }
        return result;
    }


    private boolean isValid(String s) {
        if (s.length() == 0) return true;

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty() && c == ')') {
                    return false;
                }
                else {
                    if (c == ')') {
                        stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
