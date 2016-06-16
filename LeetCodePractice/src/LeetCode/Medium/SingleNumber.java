package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * @author ASUS-PC
 *
 */
public class SingleNumber {
	
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int key : nums) {
        	if (map.containsKey(key)) {
            	map.put(key, map.get(key) + 1);
        	}
        	else {
            	map.put(key, 1);
        	}

        }

        for (Integer key: map.keySet()) {
        	if (map.get(key) <= 1) {
        		return key;
        	}
        }
        return 0;
    }
    
    /**
     * The idea hinges on 3 properties of xor. 
     * (1) that its a commutative operation (i.e. a xor b = b xor a). 
     * (2) that something xor itself is 0. So a xor a = 0. 
     * (3) 0 xor a = a. These three properties mean that 
     * 
     * a xor b xor a = a xor a xor b = 0 xor b = b. 
     * Thus it doesn't matter the order of the numbers. If something only occurs once it won't get negated.

     * @param nums
     * @return
     */
    public int singleNumber_Good(int[] nums) {
        int res = 0;
        for(int num : nums) {
            res ^= num;
        }
        return res;
    }
}
