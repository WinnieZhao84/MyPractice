package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference 
 * between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 * 
 * @author WinnieZhao
 *
 */
public class ContainsDuplicateIII {

    /**
     * O(N lg K) solution
     * This problem requires to maintain a window of size k of the previous values that can be queried for value ranges.
     * The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result
     * in time complexity O(N lg K)
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k == 0) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long) nums[i]);
            Long ceiling = set.ceiling((long) nums[i]);

            if ((floor != null && nums[i] - floor <= t ) ||
                    (ceiling != null && ceiling - nums[i] <= t)) {
                return true;
            }

            set.add((long) nums[i]);

            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 桶的方法 O(n)
     * 思想是分成t+1个桶，对于一个数，将其分到第key = num / (t + 1) 个桶中，我们只需要查找相同的和相邻的桶的元素就可以判断有无重复
     *
     * O(N) solution
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate_better(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k == 0 || t < 0) {
            return false;
        }

        Map<Long, Long> map = new HashMap<>();

        for (int i=0; i<nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1); // why t+1 ? because, if t not plus 1, when t == 0, num divide by 0 will cause crash.

            // means the key in the map duplicated, it means the must be exist two numbers that the different value between them are less than t
            if (map.containsKey(bucket)
                    // if the two different numbers are located in two adjacent bucket, the value still might be less than t
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true; // the same reason for -1
            if (i >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum); //replace the duplicated key
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII solution = new ContainsDuplicateIII();

        int[] nums = {3,4,6,5,3,2,1};

        System.out.println(solution.containsNearbyAlmostDuplicate_better(nums, 3, 2));
    }
    
}
