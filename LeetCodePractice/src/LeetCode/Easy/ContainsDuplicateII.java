package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct 
 * indices i and j in the array such that nums[i] = nums[j] and the difference between 
 * i and j is at most k.
 * 
 * @author ASUS-PC
 *
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int length = nums.length;
    	
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i=0; i<length; i++) {
    		if (map.containsKey(nums[i])) {
    			int index = map.get(nums[i]);
    			if (i-index <= k) {
    				return true;
    			}
    		}
    	    map.put(nums[i], i);

    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
    	ContainsDuplicateII solution = new ContainsDuplicateII();
        
    	int[] nums = {1,0,1,1};
        System.out.println(solution.containsNearbyDuplicate(nums , 1));
    }
}
