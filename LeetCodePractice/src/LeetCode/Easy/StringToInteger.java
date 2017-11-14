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
        if (str == null) {
            return 0;
        }
        str = str.trim();
        int length = str.length();
        if (length == 0) {
            return 0;
        }

        long res = 0;
        int sign = str.charAt(0) == '-' ? -1 : 1;
        for (int i=0; i<length; i++) {
            if (i == 0 && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
                continue;
            }
            else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }

            res = res * 10 + (str.charAt(i) - '0');

            if (res >= Integer.MAX_VALUE) {
                break;
            }

        }

        res = res * sign;

        if (res <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        else if (res >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) res;
    }
    
    public static void main(String[] args) {
    	StringToInteger solution = new StringToInteger();
    	System.out.print(solution.myAtoi("+1"));
    }
}
