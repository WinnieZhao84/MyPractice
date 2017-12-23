package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining 
 * the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * 
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.

 * @author ASUS-PC
 *
 */
public class MoveZeroes {

    /**
     * If the current element is not 0, then we need to append it just in front of last non 0 element we found.
     * After we have finished processing new elements, all the non-zero elements are already at beginning of array
     * We just need to fill remaining array with 0's.
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <=1 ) {
            return;
        }

        int len = nums.length;
        int nonZeroIndex = 0;
        for (int i=0; i<len; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i];
            }
        }

        for (int i=nonZeroIndex; i<len; i++) {
            nums[i] = 0;
        }
    }

    /**
     * The total number of operations of the previous approach is sub-optimal.
     * For example, the array which has all (except last) leading zeroes: [0, 0, 0, ..., 0, 1].
     * How many write operations to the array? For the previous approach, it writes 0's n-1n−1 times,
     * which is not necessary. We could have instead written just once.
     * How? ..... By only fixing the non-0 element,i.e., 1.
     *
     * The optimal approach is again a subtle extension of above solution. A simple realization is
     * if the current element is non-0, its' correct position can at best be it's current position or a position earlier.
     * If it's the latter one, the current position will be eventually occupied by a non-0 ,or a 0,
     * which lies at a index greater than 'cur' index. We fill the current position by 0 right away,
     * so that unlike the previous solution, we don't need to come back here in next iteration.
     *
     * In other words, the code will maintain the following invariant:
     * All elements before the slow pointer (lastNonZeroFoundAt) are non-zeroes.
     * All elements between the current and slow pointer are zeroes.
     * Therefore, when we encounter a non-zero element, we need to swap elements pointed by current and slow pointer,
     * then advance both pointers. If it's zero element, we just advance current pointer.
     *
     * Time Complexity: O(n).
     * However, the total number of operations are optimal. The total operations (array writes) that code does is Number
     * of non-0 elements.This gives us a much better best-case (when most of the elements are 0) complexity than last
     * solution. However, the worst-case (when all elements are non-0) complexity for both the algorithms is same.

     * @param nums
     */
    public void moveZeroes_better(int[] nums) {
        if (nums == null || nums.length <=1 ) {
            return;
        }

        int len = nums.length;
        int lastNonZeroFoundAt = 0;

        for (int i=0; i<len; i++) {
            if (nums[i] != 0) {
                int temp = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt++] = nums[i];
                nums[i] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();

        int[] nums = {1, 3, 0, 3, 12};
        solution.moveZeroes_better(nums);

        System.out.println(Arrays.toString(nums));
    }
}
