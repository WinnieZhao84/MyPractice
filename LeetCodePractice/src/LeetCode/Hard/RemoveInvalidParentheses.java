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

    /**
     * The good thing of using BFS is that we can guarantee the number
     * of parentheses that need to be removed is minimal,
     * also no recursion call is needed in BFS.
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        if (s == null) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        Set<String> visited = new HashSet<>();

        boolean found = false;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                String cur = queue.poll();
                
                if (this.isValid(cur)) {
                    result.add(cur);
                    found = true;
                }
                
                if (found == true) {
                    continue;
                }
                
                int len = cur.length();
                
                for (int j=0; j<len; j++) {
                    if (cur.charAt(j) != '(' && cur.charAt(j) != ')') {
                        continue;
                    }
                    String next = cur.substring(0, j) + cur.substring(j+1);
                    
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
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

    /**
     * Better isValid solution using counter instead of stack
     * @param s
     * @return
     */
    private boolean isValid_better(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses solution = new RemoveInvalidParentheses();

        System.out.println(solution.removeInvalidParentheses("()())()"));
    }
}
