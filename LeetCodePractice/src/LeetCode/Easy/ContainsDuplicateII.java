package LeetCode.Easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		if (nums == null || nums.length == 0) {
			return false;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<nums.length; i++) {

			if (map.containsKey(nums[i]) && Math.abs(i-map.get(nums[i])) <=k ) {
				return true;
			}
			map.put(nums[i], i);
		}

		return false;
    }

	public boolean containsNearbyDuplicate_better(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; i++) {
			if(i > k) {
				set.remove(nums[i-k-1]);
			}
			if(!set.add(nums[i])) {
				return true;
			}
		}
		return false;
	}
    
    public static void main(String[] args) {
    	ContainsDuplicateII solution = new ContainsDuplicateII();
        
    	int[] nums = {1,0,1,1};
        System.out.println(solution.containsNearbyDuplicate(nums , 1));
    }
}
