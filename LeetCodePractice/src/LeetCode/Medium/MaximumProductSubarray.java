package LeetCode.Medium;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

 * @author WinnieZhao
 *
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        int res = nums[0];

        for (int i=1; i<nums.length; i++) {
            int temp = max;

            max = Math.max(nums[i], Math.max(min*nums[i], max*nums[i]));
            min = Math.min(nums[i], Math.min(min*nums[i], temp*nums[i]));

            res = Math.max(max, res);
        }

        return res;
    }
    
    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        int[] nums = {2,3,-2,-4};
        System.out.println(solution.maxProduct(nums));
    }
}
