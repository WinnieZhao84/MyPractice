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
        int result = 0;
        
        if (str == null) {
        	return result;
        }
        str = str.trim();
        int length = str.length();
        if (length == 0) {
        	return result;
        }
        
        int sign = 1;
        if (str.charAt(0) == '-') {
        	sign = -1;
        }
        
        long resultLong = 0;
        for (int i=0; i<=length-1; i++) {
        	char ch = str.charAt(i);
        	
        	if (i==0 && (ch == '+' || ch == '-')) {
        		continue;
        	}
        	if (ch < '0' || ch > '9') {
        		break;
        	}
        	resultLong = resultLong * 10 + (ch - '0');
        	if (resultLong > Integer.MAX_VALUE) {
        		break;
        	}
        }
        
        
        resultLong = resultLong * sign;
        if (resultLong > Integer.MAX_VALUE) {
        	return Integer.MAX_VALUE;
        }
        else if (resultLong < Integer.MIN_VALUE) {
        	return Integer.MIN_VALUE;
        }
        result = (int) resultLong;
        
        return result;
    }
    
    public static void main(String[] args) {
    	StringToInteger solution = new StringToInteger();
    	System.out.print(solution.myAtoi("    010"));
    }
}
