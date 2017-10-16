package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value".
 * The equation contains only '+', '-' operation, the variable x and its coefficient.
 *
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 *
 * Example 1:
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 *
 * Example 2:
 * Input: "x=x"
 * Output: "Infinite solutions"
 *
 * Example 3:
 * Input: "2x=x"
 * Output: "x=0"
 *
 * Example 4:
 * Input: "2x+3x-6x=x+2"
 * Output: "x=-1"
 *
 * Example 5:
 * Input: "x=x+2"
 * Output: "No solution"

 * Created by WinnieZhao on 7/10/2017.
 */
public class SolveTheEquation {

    /**
     * Every x in the left hand side of the given equation is treated as positive,
     * while that on the right hand side is treated as negative, in the current implementation.
     * Likewise, every number on the left hand side is treated as negative,
     * while that on the right hand side is treated as positive. Thus, by doing so,
     * we obtain all the x's in the new lhs and all the numbers in the new rhs of the original equation.
     *
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        String[] expression = equation.split("=");
        int lhs = 0, rhs = 0;

        String left = expression[0];
        String right = expression[1];

        for (String x: breakIt(left)) {
            if (x.indexOf("x") >= 0) {
                lhs += Integer.parseInt(coeff(x));
            }
            else
                rhs -= Integer.parseInt(x);
        }
        for (String x: breakIt(right)) {
            if (x.indexOf("x") >= 0)
                lhs -= Integer.parseInt(coeff(x));
            else
                rhs += Integer.parseInt(x);
        }
        if (lhs == 0) {
            if (rhs == 0)
                return "Infinite solutions";
            else
                return "No solution";
        }
        return "x=" + rhs / lhs;
    }

    private List<String> breakIt(String s) {
        List<String > res = new ArrayList<>();
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (r.length() > 0)
                    res.add(r);
                r = "" + s.charAt(i);
            }
            else {
                r += s.charAt(i);
            }
        }
        res.add(r);
        return res;
    }

    public String solveEquation_test(String equation) {
        if (equation == null || equation.length() == 0) {
            return "No solution";
        }

        String[] parts = equation.split("=");
        String left = parts[0];
        String right = parts[1];

        int leftWithX = 0;
        int rightWithX = 0;
        int leftNoX = 0;
        int rightNoX = 0;

        for (String s : parse(left)) {

            if (s.contains("x")) {
                s = s.replace("x", "");
                s = s.isEmpty()  ? "1" : s;
                s = s.equals("-") ? "-1" : s;
                leftWithX += Integer.valueOf(s);
            }
            else {
                leftNoX += Integer.valueOf(s);
            }

        }

        for (String s : parse(right)) {
            if (s.contains("x")) {
                s = s.replace("x", "");
                s = s.isEmpty()  ? "1" : s;
                s = s.equals("-") ? "-1" : s;
                rightWithX += Integer.valueOf(s);
            }
            else {
                rightNoX += Integer.valueOf(s);
            }
        }


        int countX = leftWithX + -1 * rightWithX;
        int value = leftNoX * -1 + rightNoX;

        if (countX == 0) {
            if (value != 0) {
                return  "No solution";
            }
            return "Infinite solutions";
        }

        return "x=" + value / countX;
    }

    private List<String> parse(String s) {
        List<String> res = new ArrayList<>();

        if (s.contains("-")) {
            s = s.replaceAll("-","+-");
        }

        String[] s1 = s.split("\\+");

        for (String x : s1) {
            if (!x.isEmpty()) {
                res.add(x);
            }
        }

        return res;
    }

    private String coeff(String x) {
        if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9')
            return x.replace("x", "");
        return x.replace("x", "1");
    }

    public static void main(String[] args) {
        String expression = "-x=-1";

        SolveTheEquation solution = new SolveTheEquation();
        System.out.println(solution.solveEquation_test(expression));
    }
}
