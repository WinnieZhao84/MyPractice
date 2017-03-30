package LeetCode.Medium;

import java.util.Arrays;

/**
 * 280
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4]. the pattern is number in odd position is peak.
 *
 * Created by WinnieZhao on 2017/3/29.
 */
public class WiggleSort {

    /*
     * First try to solve it without in-place:
     * sort the array in increasing order.
     * create a result array of the same size.
     * keep 2 pointers, one from the beginning, one from the middle(notice odd/even of array).
     * put beginning first, then the middle pointer, into the result array.
     */

    // Sort first => nlogn
    public void wiggleSort(int[] nums) {

        Arrays.sort(nums);

        for(int i = 2; i < nums.length; i+=2){
            int tmp = nums[i-1];
            nums[i-1] = nums[i];
            nums[i] = tmp;
        }
    }
    /**
     * noticing that the property of wiggle array is:
     * if i%2 == 1, nums[i] >= nums[i-1];
     * if i%2 == 0, nums[i] <= nums[i-1];
     * @param nums
     */
    public void wiggleSort_better(int[] nums) {
        for(int i=1; i< nums.length;i++){
            if( (i&1) == 1 && nums[i] < nums[i-1] || (i&1) == 0 && nums[i] > nums[i-1] ) {
                int t = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = t;
            }
        }
    }

    public static void main(String[] args) {
        WiggleSort solution = new WiggleSort();

        int[] nums = {3, 5, 2, 1, 6, 4};
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
