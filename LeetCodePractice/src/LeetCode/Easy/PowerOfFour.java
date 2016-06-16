package LeetCode.Easy;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * 
 * Follow up: Could you solve it without loops/recursion?
 * 
 * @author ASUS-PC
 *
 */
public class PowerOfFour {

    public boolean isPowerOfFour(int num) {
    	if (num<=0) return false;
    	if (num==1) return true;

        while (num >= 4) {
        	if (num%4 != 0) {
        		return false;
        	}
        	num = num/4;
        }
        
        return num == 1; 
    }
    
    /**
     * Solution 1: convert it into radix 4 number, and check if it is in format "1", "10", "100", "1000" ... etc.
     * Time is O( log n) due to toString and matches both cost O( log n).
     * 
     * @return
     */
    public boolean isPowerOfFour_better(int num) {
        return Integer.toString(num, 4).matches("10*");
    }

    /**
     * Solution 2: check if its binary has only ONE "1" bit and EVEN number of trailing zeros. 
     * Theory is similar to above, but no need of conversion into binary.
     *
     */
    public boolean isPowerOfFour_better1(int num) {
        return (Integer.bitCount(num) == 1) && ((Integer.numberOfTrailingZeros(num) % 2) == 0);
    }
    
    public static void main(String[] args) {
    	PowerOfFour solution = new PowerOfFour();
        System.out.println(solution.isPowerOfFour(4));

    }
}
