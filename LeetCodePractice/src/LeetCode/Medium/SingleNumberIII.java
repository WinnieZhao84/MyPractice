package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and 
 * all the other elements appear exactly twice. Find the two elements that appear only once.
 * 
 * For example:
 * 
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * 
 * Note:
 * 
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only 
 * constant space complexity?
 * 
 * @author ASUS-PC
 *
 */
public class SingleNumberIII {

	/**
	 * In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
	 * Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result.
	 * Find out an arbitrary set bit (for example, the rightmost set bit).
	 *
	 * In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with the aforementinoed bit unset.
	 * Two different numbers we need to find must fall into thte two distinct groups. XOR numbers in each group, we can find a number in either group.
	 *
	 * 3 011
	 * 5 101
	 * 
	 * 3^5 = 110
	 */
    public int[] singleNumber(int[] nums) {
    	int[] result = new int[2];
        int res = 0;
        
        for (int num : nums) {
        	res = res ^ num;
        }

        // int[] nums = {1, 2, 1, 3, 2, 5};
        // 001, 010, 001, 011, 010, 101
        // 3^5 = 011 ^ 101 = 110
        int num1=0;
        int num2=0;
        
        int lastBit = 0;
        for (int i=0; i<32; i++) {
        	lastBit = (res >> i) & 1;
        	if (lastBit == 1) {
            	lastBit = lastBit << i;
            	break;
        	}
        }
        
        for (int num : nums) {
        	if ((num & lastBit) == 0) {
        		num1 = num1 ^ num;
        	}
        	else {
        		num2 = num2 ^ num;
        	}
        }
        
        result[0] = num1;
        result[1] = num2;
        
        return result;
    }
    
    public static void main (String[] args) {
    	SingleNumberIII solution = new SingleNumberIII();
    	
    	int[] nums = {1, 2, 1, 3, 2, 5};
    	int[] result = solution.singleNumber(nums);
    	
    	System.out.println(Arrays.toString(result));
    	//System.out.println(3^5);
    }
}
