package LeetCode.Hard;

import java.util.Stack;

/**
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Note: Do not use the eval built-in library function.
 * Created by WinnieZhao on 2017/5/1.
 */
public class BasicCalculator {

    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int num = 0;
        int sign = 1;

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            else if (ch == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            }
            else if (ch == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            }
            else if (ch == '(') {
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;
            }
            else if (ch == ')') {

                result += sign * num;
                num = 0;

                result *= stack.pop();
                result += stack.pop();
            }

        }
        if(num != 0) result += sign * num;
        return result;
    }

    public static void main (String[] args) {
        BasicCalculator solution = new BasicCalculator();

        System.out.println(solution.calculate("1-(5)"));
    }

}
