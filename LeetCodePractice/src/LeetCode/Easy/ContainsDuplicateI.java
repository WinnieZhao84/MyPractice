package LeetCode.Easy;

import java.util.Arrays;
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

    /**
     * Time complexity : O(nlogn). Sorting is O(nlogn) and the sweeping is O(n).
     * The entire algorithm is dominated by the sorting step, which is O(nlogn).
     *
     * Space complexity : O(1).

     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    /**
     * Time complexity : O(n). We do search() and insert() for nn times and each operation takes constant time.
     * Space complexity : O(n). The space used by a hash table is linear with the number of elements in it.
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate_better(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x: nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }
}
