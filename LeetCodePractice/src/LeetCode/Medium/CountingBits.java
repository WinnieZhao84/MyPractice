package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ¡Ü i ¡Ü num calculate 
 * the number of 1's in their binary representation and return them as an array.
 * 
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * 
 * Follow up:
 * 
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time 
 * O(n) /possibly in a single pass?
 * 
 * Space complexity should be O(n).
 * 
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * 
 * Hint:
 * 
 * You should make use of what you have produced already.
 * 
 * Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 * Or does the odd/even status of the number help you in calculating the number of 1s?

 * @author ASUS-PC
 *
 */
public class CountingBits {
    /**
     * 0   000 0
     * 1   001 1
     * 2   010 1
     * 3   011 2
     * 4   100 1
     * 5   101 2
     * 6   110 2
     * 7   111 3
     * 8  1000 1
     * 9  1001 2
     * 10 1010 2
     * 11 1011 3
     * 12 1100 2
     * 13 1101 3
     * 
     * @param num
     * @return
     */
    public int[] countBits(int num) {
    	
    	int[] result = new int[num+1];
    	int rangeBit = 1;
    	
    	result[0] = 0;
    	int i = 1;
    	while (i <= num) {
    		for (int j = 0; j<rangeBit && i <=num; j++) {
    			result[i++] = 1 + result[j];
    		}
    		rangeBit = rangeBit << 1;
    	}
        return result;
    }

    public static void main (String[] args) {
    	CountingBits solution = new CountingBits();
    	
    	int[] result = solution.countBits(8);
    	
    	System.out.print(Arrays.toString(result));
    }
}
