package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author WinnieZhao
 *
 */
public class IntegerToRoman {

    public String intToRoman(int num) {
        if (num == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int i=0;
        while (num > 0) {
            while (i<vals.length && num >= vals[i]) {
                sb.append(strs[i]);
                num = num - vals[i];
            }
            i++;
        }

        return sb.toString();
    }
    
    private static final int[] vals =      new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    private static final String[] strs = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    
    public static void main(String args[]) {
        IntegerToRoman solution = new IntegerToRoman();
        System.out.println(solution.intToRoman(1));
    }
}
