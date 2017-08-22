package LeetCode.Hard;

/**
 *
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.

 * Created by WinnieZhao on 2017/4/26.
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 1;
        }

        int length = nums.length;
        /**
         * Put each number in its right place.
         * For example:
         * When we find 5, then swap it with A[4].
         * At last, the first place where its number is not right, return the place + 1.
         */
        for (int i=0; i<length; i++) {
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i]-1]) {
                this.swap(i, nums[i]-1, nums);
            }
        }

        for (int i=0; i<length; i++) {
            if (nums[i] != i + 1) {
                return i+1;
            }
        }

        return length + 1;

    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive solution = new FirstMissingPositive();

        int[] nums = {2,1,0};
        System.out.println(solution.firstMissingPositive(nums));

        int[] nums1 = {3,4,-1,1};
        System.out.println(solution.firstMissingPositive(nums1));
    }
}
