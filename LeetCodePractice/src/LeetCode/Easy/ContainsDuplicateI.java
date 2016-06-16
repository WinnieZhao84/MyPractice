package LeetCode.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 * 
 * @author ASUS-PC
 *
 */
public class ContainsDuplicateI {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for (int i : nums) {
            s.add(i);
        }
        
        return s.size() < nums.length;
    }
}
