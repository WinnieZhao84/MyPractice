package LeetCode.Medium;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * @author ASUS-PC
 *
 */
public class SingleNumberII {

	/**
	 * sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit,
	 * if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot.
	 *
	 * This has complexity of O(32n), which is essentially O(n) and very easy to think and implement.
	 * Plus, you get a general solution for any times of occurrence.
	 * Say all the numbers have 5 times, just do sum %= 5.

	 * 111
	 * 110
	 * 111
	 * 111
	 * 
	 * @param nums
	 * @return
	 */
    public int singleNumber(int[] nums) {

    	int result = 0;
    	for (int i=0; i<32; i++) {
        	int sum = 0;
    		for(int num : nums) {

				/**
				 * For all nums, get number of 1 for each position one by one.
				 * Sum all "1" for all nums from each position. Mod 3 to find
				 * the remaining "1" or "0" for that position. Move it back to
				 * original position of num. Sum that up and the result will
				 * be the single number!!
				 */
				int bit = (num >> i) & 1;
				sum += bit;
    		}

			sum = sum % 3;
			result += sum << i;
    	}
    	
		return result;
    }
    
    public static void main (String[] args) {
        SingleNumberII solution = new SingleNumberII();
        
        // 011, 011, 011, 100
        int[] nums = {3,3,3,4};
        int result = solution.singleNumber(nums);
    	
        System.out.println(result);
        //System.out.println(3^5);
    }
}
