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
        
		if (s == null || s.length() <=1 ){
			return false;
		}
		
		Stack<Character> stack = new Stack<Character>();

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
	
    public static void main(String[] args) {
    	ValidParentheses solution = new ValidParentheses();
    	System.out.print(solution.isValid("()]{}"));
    }
}
