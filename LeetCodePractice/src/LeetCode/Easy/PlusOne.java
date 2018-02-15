package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
**/
public class PlusOne {
    public int[] plusOne(int[] digits) {

        if (digits == null || digits.length == 0) {
            return new int[0];
        }

        int carry = 1;
        for (int i=digits.length-1; i>=0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }

        if (carry == 0) {
            return digits;
        }
        else {
            int[] res = new int[digits.length+1];
            res[0] = carry;

            return res;
        }
    }
    
    
    public static void main(String[] args) {
    	PlusOne solution = new PlusOne();
    	
    	int[] nums = {9, 9};
    	nums = solution.plusOne(nums);
    	
        System.out.println(Arrays.toString(nums));

    }
}
