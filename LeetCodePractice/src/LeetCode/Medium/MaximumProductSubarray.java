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
        if (nums == null || nums.length == 0) return 0;
        
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        for (int i=1; i<nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            
            if (max > result) {
                result = max;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        int[] nums = {2,3,-2,4};
        System.out.println(solution.maxProduct(nums));
    }
}
