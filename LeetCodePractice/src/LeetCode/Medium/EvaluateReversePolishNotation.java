package LeetCode.Medium;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 * 
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @author WinnieZhao
 *
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        
        if (tokens == null) return 0;
        
        Stack<String> stack = new Stack<>();
        for (String ch : tokens) {
            if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")) {
                String num1 = stack.pop();
                String num2 = stack.pop();
                
                int res = 0;
                switch(ch) {
                    case "+": {
                        res = Integer.valueOf(num1) + Integer.valueOf(num2);
                        break;
                    }
                    case "-": {
                        res = Integer.valueOf(num2) - Integer.valueOf(num1);
                        break;
                    }
                    case "/": {
                        res = Integer.valueOf(num2) / Integer.valueOf(num1);
                        break;
                    }
                    case "*": {
                        res = Integer.valueOf(num2) * Integer.valueOf(num1);
                        break;
                    }
                    
                }
                stack.push(String.valueOf(res));
            }
            else {
                stack.push(ch);
            }
        }
        
        int result = Integer.valueOf(stack.pop());
        return result;
    }
    
    public static void main(String[] args) {
        EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
        
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(solution.evalRPN(tokens));
        
        String[] tokens1 = {"4", "13", "5", "/", "+"};
        System.out.println(solution.evalRPN(tokens1));
    }
    
}
