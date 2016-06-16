package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
**/
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        
        int carrier = 1;
        for (int i=length-1; i>=0; i--) {
        	int digit = digits[i];   	
        	digit += carrier;
        	digits[i] = digit % 10;
        	
        	if (digit > 9) {
        		carrier = 1;
        	} 
        	else {
        		carrier = 0;
        		break;
        	}

        }
        
        if (carrier == 1) {
        	int[] result = new int[digits.length + 1];
            System.arraycopy(digits, 0, result, 1, digits.length);
            result[0] = 1;
            return result;
        }
        else {
        	return digits;
        }
    }
    
    
    public static void main(String[] args) {
    	PlusOne solution = new PlusOne();
    	
    	int[] nums = {9, 9};
    	nums = solution.plusOne(nums);
    	
        System.out.println(Arrays.toString(nums));

    }
}
