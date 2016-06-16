package LeetCode.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process: 
 * Starting with any positive integer, replace the number by the sum 
 * of the squares of its digits, and repeat the process until the number 
 * equals 1 (where it will stay), or it loops endlessly in a cycle 
 * which does not include 1. Those numbers for which this process 
 * ends in 1 are happy numbers.
 * 
 * Example: 19 is a happy number
 * 
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1

 * @author ASUS-PC
 *
 */
public class HappyNumber {
	Set<Integer> visited = new HashSet<Integer>();
	
    public boolean isHappy(int n) {
        
    	int result = 0;
    	
    	String nstr = String.valueOf(n);
    	
    	for (int i=0; i<=nstr.length()-1; i++) {
    		int digit = nstr.charAt(i) - '0';
    		result += digit * digit;
    	}
    	
    	if (result == 1) {
    		return true;
    	}
    	else {
    		if (visited.contains(result)) {
    			return false;
    		}
        	visited.add(n);
    		return this.isHappy(result);
    	}
    }
    
    public static void main(String[] args) {
    	HappyNumber solution = new HappyNumber();
    	System.out.print(solution.isHappy(109));
    }
}
