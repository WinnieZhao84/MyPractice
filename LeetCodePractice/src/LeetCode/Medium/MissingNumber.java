package LeetCode.Medium;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * 
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * 
 * @author WinnieZhao
 *
 */
public class MissingNumber {
    
    public int missingNumber(int[] nums) {
        
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
        
    }
    
    public static void main(String[] args) {
        MissingNumber solution = new MissingNumber();
        int[] nums = {1,0,3};
        System.out.println(solution.missingNumber(nums));
    }
}
