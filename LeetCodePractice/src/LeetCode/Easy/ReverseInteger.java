package LeetCode.Easy;

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
Example2: x = -123, return -321

 * @author ASUS-PC
 *
 */
public class ReverseInteger {

    public int reverse(int x) {
    	int max = Integer.MAX_VALUE;
    	
        long result = 0;
        
        int value = Math.abs(x);
    	while (value > 0) {
    		int y = value%10;
    		result = result * 10 + y;
    		if (result > max) {
    			return 0;
    		}
    		value = value/10;
    	}
    	
    	if (x < 0) {
    		return 0 - (int)result;
    	}
    	return (int) result;
    }
    
    public static void main(String[] args) {
    	ReverseInteger solution = new ReverseInteger();
    	System.out.print(solution.reverse(1000000003));
    }
}
