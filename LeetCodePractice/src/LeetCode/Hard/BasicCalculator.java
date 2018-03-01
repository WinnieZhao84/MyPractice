package LeetCode.Hard;

import java.util.Stack;

/**
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces.
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
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int num = 0;
        int res = 0;

        Stack<Integer> stack = new Stack<>();
        int sign = 1;

        char[] chs = s.toCharArray();

        for (int i=0; i<chs.length; i++) {
            char ch = chs[i];

            if (ch >= '0' && ch <= '9') {
                num = num*10 + (ch - '0');
            }
            else if (ch == '+') {
                res += sign * num;
                num = 0;
                sign = 1;
            }
            else if (ch == '-') {
                res += sign * num;
                num = 0;
                sign = -1;
            }
            else if (ch == '(') {
                stack.push(res);
                stack.push(sign);

                res = 0;
                num = 0;
                sign = 1;
            }
            else if (ch == ')') {
                res += num * sign;

                res  = res * stack.pop();
                res += stack.pop();

                num = 0;
            }
        }

        if (num != 0) {
            res += sign * num;
        }

        return res;
    }

    public static void main (String[] args) {
        BasicCalculator solution = new BasicCalculator();

        System.out.println(solution.calculate("2147483647"));
    }

}
