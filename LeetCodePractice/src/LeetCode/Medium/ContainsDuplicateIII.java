package LeetCode.Medium;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference 
 * between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 * 
 * @author WinnieZhao
 *
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2 || k == 0) {
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();

        int i = 0;
        while (i < nums.length) {
            Long floor = set.floor((long) nums[i]);
            Long ceiling = set.ceiling((long) nums[i]);
            
            if ((floor != null && nums[i] - floor <= t ) ||
                    (ceiling != null && ceiling - nums[i] <= t)) {
                return true;
            }
            
            set.add((long) nums[i++]);
            
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
        }
        return false;
    }
    
}
