package LeetCode.Interview.Microsoft.LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 772
 *
 * Implement a basic calculator, supporting operators such as +, -, *, / and operands like ( and ).
 *
 * For example: "(1+2) *10 -25/(1-7)" -> 34
 *
 */
public class BasicCalculatorIII {
    public int calculate(String s) {

        char [] charArr = s.toCharArray();
        int [] calRes = calculate(charArr, 0, s.length() - 1);
        return calRes[0];
    }

    private int [] calculate(char [] arr, int start, int end) {
        int i = start;
        Character sign = '+';
        Deque<Integer> stack = new LinkedList<>();

        while (i <= end) {
            if (arr[i] == '(') {
                int[] preRes = calculate(arr, i + 1, end);
                int num = preRes[0];
                if (sign == '*') {
                    stack.offerFirst(num * stack.pollFirst());
                } else if (sign == '/') {
                    stack.offerFirst(stack.pollFirst() / num);
                } else if (sign == '-') {
                    stack.offerFirst(-1 * num);
                } else {
                    stack.offerFirst(num);
                }
                i = preRes[1];
            }
            else if (Character.isDigit(arr[i])) {
                int num = 0;
                while (i <= end && Character.isDigit(arr[i])) {
                    num = num * 10 + arr[i] - '0';
                    i++;
                }
                if (sign == '*') {
                    stack.offerFirst(num * stack.pollFirst());
                } else if (sign == '/') {
                    stack.offerFirst(stack.pollFirst() / num);
                } else if (sign == '-') {
                    stack.offerFirst(-1 * num);
                } else {
                    stack.offerFirst(num);
                }
                continue;
            }
            else if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/') {
                sign = arr[i];
            }
            else if (arr[i] == ')') {
                break;
            }
            i++;
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }

        return new int[]{res, i};
    }
}
