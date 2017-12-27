package LeetCode.Medium;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * 
 * Note: Do not use the eval built-in library function.
 * 
 * @author WinnieZhao
 *
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();

        int num = 0;
        char sign = '+';
        for (int i=0; i<chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)){
                num = num*10 + s.charAt(i) -'0';
            }

            if ((!Character.isDigit(ch) && ch != ' ' )|| i==chars.length-1) {
                if (sign == '*') {
                    stack.push(stack.pop()*num);
                }
                else if (sign == '/') {
                    stack.push(stack.pop()/num);
                }
                else if (sign == '-') {
                    stack.push(-num);
                }
                else if (sign == '+') {
                    stack.push(num);
                }
                num = 0;
                sign = chars[i];
            }
        }

        int res = 0;
        for(int i : stack){
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        BasicCalculatorII solution = new BasicCalculatorII();
        System.out.println(solution.calculate("3+2*2"));
    }
}
