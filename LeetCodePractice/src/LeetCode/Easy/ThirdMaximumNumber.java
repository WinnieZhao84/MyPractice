package LeetCode.Easy;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. 
 * The time complexity must be in O(n).
 * 
 * Example 1: Input: [3, 2, 1]
 * Output: 1
 * Explanation: The third maximum is 1.
 * 
 * Example 2: Input: [1, 2]
 * Output: 2
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * 
 * Example 3:
 * Input: [2, 2, 3, 1] Output: 1
 * 
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.

 * @author WinnieZhao
 *
 */
public class ThirdMaximumNumber {

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums) {
            if (!set.contains(n)) {
                queue.offer(n);
                if (queue.size() > 3) {
                    queue.poll();
                }
            }
            set.add(n);
        }
        
        if (queue.size() < 3) {
            while (queue.size() > 1) {
                queue.poll();
            } 
        }
        
        return queue.peek();
    }

    public int thirdMax_better(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;

        for (Integer num : nums) {

            if (num.equals(max1) || num.equals(max2) || num.equals(max3)) {
                continue;
            }
            else if (max1 == null || num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            }
            else if (max2 == null || num > max2) {
                max3 = max2;
                max2 = num;
            }
            else if (max3 == null || num > max3) {
                max3 = num;
            }

        }

        if (max3 == null) {
            return max1;
        }

        return max3;
    }
    
    public static void main(String[] args) {
        ThirdMaximumNumber solution = new ThirdMaximumNumber();
        int[] nums = {1, 2};
        System.out.println(solution.thirdMax(nums));
    }
}
