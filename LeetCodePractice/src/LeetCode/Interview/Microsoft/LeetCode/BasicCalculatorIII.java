package LeetCode.Interview.Microsoft.LeetCode;

import java.util.Stack;

/**
 * 772
 *
 * Implement a basic calculator, supporting operators such as +, -, *, / and operands like ( and ).
 *
 * For example: "(1+2) *10 -25/(1-7)" -> 34
 *
 * 思路就是两个stack，一个存数字一个存符号。如果遇到数字直接存到数字stack；如果遇到符号，有几种情况：
 *
 * 1.当前符号比上一个符号优先级高，比如* 高于+，那么直接进栈
 * 2.当前符号低于上一个，那么就要把所有已经在stack里面优先于当前符号的全算完，再推进当前符号
 * 3.当前符号是“（”，直接push
 * 4.当前符号是“）”，就要把所有“（”以前的符号全部算完
 *
 */
public class BasicCalculatorIII {

    public static void main(String[] args) {
        BasicCalculatorIII solution = new BasicCalculatorIII();

        System.out.println(solution.calculate("(1+2)*10-25/((7-7) + 5)"));
    }

    public int calculate(String s) {

        Stack<Integer> integers = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int i = 0;
        int num = 0;
        boolean hasDigit = false;

        while (i < s.length()) {
            char cur = s.charAt(i);

            if (this.isDigit(cur)) {
                num = num * 10 + (cur - '0');
                hasDigit = true;
            }
            else {
                if (hasDigit) {
                    hasDigit = false;
                    integers.push(num);
                    num = 0;
                }

                if (this.isOp(cur)) {
                    if (cur == '(') {
                        ops.push('(');
                    }
                    else if (cur == ')') {
                        while (!ops.isEmpty() && ops.peek() != '(') {
                            integers.push(this.calc(integers.pop(), integers.pop(), ops.pop()));
                        }
                        ops.pop();
                    }
                    else {// +, -, *, /
                        while(!ops.isEmpty() && this.precede(cur, ops.peek())) {
                            integers.push(this.calc(integers.pop(), integers.pop(), ops.pop()));
                        }
                        ops.push(cur);
                    }
                }
            }
            i++;
        }

        while (!ops.isEmpty()) {
            integers.push(calc(integers.pop(), integers.pop(), ops.pop()));
        }

        return integers.isEmpty()? 0 : integers.pop();
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isOp(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
            return true;
        }
        return false;
    }

    private int calc(int first, int second, char op) {
        if (op == '-') {
            return second - first;
        }
        else if (op == '*') {
            return first * second;
        }
        else if (op == '/') {
            return second / first;
        }

        return first + second;
    }

    private boolean precede(char first, char second) {
        if (second == '*' || second == '/') {
            return true;
        }
        if (second == '+' || second == '-') {
            if (first == '*' || first == '/') {
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
