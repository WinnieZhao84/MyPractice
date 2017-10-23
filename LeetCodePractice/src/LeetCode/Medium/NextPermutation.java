package LeetCode.Medium;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1

 * Created by WinnieZhao on 10/22/2017.
 */
public class NextPermutation {

    /**
     * 1) Compare from the end from the array, find the first num which is less than its following numbers
     *    e.g: .....5, 7, 6, 4, 3, 2; got 5
     * 2) Still from the end of the array, find the first one which is greater than the selected num from step 1.
     *    got 6.
     * 3) Swap the "6" and "5" => ......6, 7, 5, 4, 3, 2;
     * 4) Reverse the array from the next index position from the step 1 => ...... 6, 2, 3, 4, 5, 7
     * The result is the next permutation for this array.
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = nums.length-2;
        while (i>=0 && nums[i+1] <= nums[i]) {
            i--;
        }

        if (i >=0) {
            for (int j=nums.length-1; j>i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        this.reverse(nums, i+1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void reverse(int[] nums, int i) {
        int j = nums.length-1;
        while (i < j) {
            swap(nums, i, j);
            j--;
            i++;
        }
    }

    public static void main (String[] args) {
        NextPermutation solution = new NextPermutation();

        int[] nums = {3,2,1};
        solution.nextPermutation(nums);

        System.out.println(Arrays.toString(nums));
    }
}
