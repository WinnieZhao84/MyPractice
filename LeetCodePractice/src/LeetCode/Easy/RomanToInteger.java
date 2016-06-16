package LeetCode.Easy;

/**
 * Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 * @author ASUS-PC
 *
 */
public class RomanToInteger {
    public int romanToInt(String s) {
    	int result = 0;
    	
    	if (s.length() == 1) {
    		result = this.getNumbers(s.charAt(0));
    		return result;
    	}
    	int length = s.length();
        int previousValue = 0;
        int currentValue = 0;
        char previousRomainSign = s.charAt(0);
        
        for (int i = 1; i < length; i++) {
    		char current = s.charAt(i);
    		
    		currentValue = this.getNumbers(current);
    		previousValue = this.getNumbers(previousRomainSign);
    		
    		if (previousValue >= currentValue) {
    			result += previousValue;
    		}
    		else {
    			result -= previousValue;
    		}
    		previousRomainSign = s.charAt(i);
    	}
        result += currentValue;
    	return result;
    }
    
    // I(1)£¬V(5)£¬X(10)£¬L(50)£¬C(100)£¬D(500)£¬M(1000)
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
