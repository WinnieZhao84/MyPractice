package LeetCode.Easy;

/**
 * Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, 
please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
 You are responsible to gather all the input requirements up front.
 * @author ASUS-PC
 *
 */
public class StringToInteger {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        long res = 0;
        boolean sign = true;
        char[] chars = str.trim().toCharArray();
        for (int i=0; i<chars.length; i++) {
            char ch = chars[i];

            if (i ==0 && ch  == '-') {
                sign = false;
                continue;
            }
            else if (i == 0 && ch == '+') {
                continue;
            }
            else if (ch < '0' || ch > '9') {
                break;
            }
            res = res * 10 + (ch - '0');

            if (res > Integer.MAX_VALUE) {
                break;
            }
        }

        if (!sign) {
            res = res * -1;
        }

        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        else if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) res;
    }
    
    public static void main(String[] args) {
    	StringToInteger solution = new StringToInteger();
    	System.out.print(solution.myAtoi("+-2"));
    }
}
