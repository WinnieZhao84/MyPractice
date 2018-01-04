package LeetCode.Easy;

/**
 * Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 * @author ASUS-PC
 *
 */
public class RomanToInteger {
    public int romanToInt(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}

		if (s.length() == 1) {
			return this.getNumbers(s.charAt(0));
		}

		char[] chars = s.toCharArray();

		int res=0;
		int prev=0;
		int cur=0;
		for (int i=1; i<chars.length; i++) {
			prev = getNumbers(chars[i-1]);
			cur = getNumbers(chars[i]);

			if (prev < cur) {
				res -= prev;
			}
			else {
				res += prev;
			}
		}

		res += cur;
		return res;
    }
    
    // I(1) V(5) X(10) L(50) (100) D(500) M(1000)
    private int getNumbers(char letter) {
        switch(letter) {
	    	case 'I': return 1;
	    	case 'V': return 5;
	    	case 'X': return 10;
	    	case 'L': return 50;
	    	case 'C': return 100;
	    	case 'D': return 500;
	    	case 'M': return 1000;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
    	RomanToInteger solution = new RomanToInteger();
    	System.out.print(solution.romanToInt("MCMXCVI"));
    }
}
