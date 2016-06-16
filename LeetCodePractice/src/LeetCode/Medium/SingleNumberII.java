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
    			
        		int bit = (num >> i) & 1;
        		sum = sum + bit;
    		}
    		
    		result += (sum % 3) << i;
    	}
    	
    	return result;
    }
    
    public static void main (String[] args) {
    	SingleNumberII solution = new SingleNumberII();
    	
    	// 011, 011, 011, 100
    	int[] nums = {3,3,3,4,4,4,7};
    	int result = solution.singleNumber(nums);
    	
    	System.out.println(result);
    	//System.out.println(3^5);
    }
}
