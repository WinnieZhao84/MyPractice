package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1. 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * @author WinnieZhao
 *
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        if(nums.length < 3) return 0;
        Arrays.sort(nums);
        
        int res = nums[0] + nums[1] + nums[2];
        
        for(int i=0; i<nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                
                int sum = nums[left] + nums[right] + nums[i];
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }
                
                if (sum == target){
                    return target;
                }
                else if (sum > target){
                    right--;
                }
                else{
                    left++;
                }
            }
            while(i + 1 < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        ThreeSumClosest solution = new ThreeSumClosest();
        int[] nums = {-1,2,1,-4};
        
        System.out.println(solution.threeSumClosest(nums, 1));
    }
}
