package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * 
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * 
 * Hint:
 * No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
 * Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
 * Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 * 
 * @author WinnieZhao
 *
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 1) {
            return String.valueOf(numerator);
        }

        StringBuilder sb = new StringBuilder();
        String sign = (numerator > 0 && denominator > 0)
                || (numerator < 0 && denominator < 0) ? "" : "-";
        sb.append(sign);

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        long intVal = num / den;
        long remain = num % den;

        // Only Integer exists
        if (remain == 0) {
            return String.valueOf(intVal);
        }

        sb.append(intVal);
        sb.append(".");

        Map<Long, Integer> map = new HashMap<>();
        map.put(remain, sb.length());

        while(remain != 0) {
            remain = remain * 10;
            sb.append(remain / den);
            remain = remain % den;

            if (map.containsKey(remain)) {
                int index = map.get(remain);
                sb.insert(index, "(");
                sb.append(")");
                break;
            }
            else {
                map.put(remain, sb.length());
            }
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        FractionToRecurringDecimal solution = new FractionToRecurringDecimal();
        System.out.println(solution.fractionToDecimal(22, 7));
    }
}
