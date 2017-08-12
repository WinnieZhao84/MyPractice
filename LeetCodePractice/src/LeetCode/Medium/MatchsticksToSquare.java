package LeetCode.Medium;

import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, 
 * please find out a way you can make one square by using up all those matchsticks. You should not break any stick, 
 * but you can link them up, and each matchstick must be used exactly one time.
 * 
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be
 * true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * 
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * 
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * 
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * 
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * 
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 * 
 * @author WinnieZhao
 *
 */
public class MatchsticksToSquare {

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        
        long sum=0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 4 != 0) return false;
        
        long width = sum/4;
        Arrays.sort(nums);
        
        long sum1=0,sum2=0,sum3=0,sum4=0;
        
        return this.dfs(nums, nums.length-1, sum1, sum2, sum3, sum4, width);
    }
    
    private boolean dfs(int[] nums, int index, long sum1, long sum2, long sum3, long sum4, long width) {
        if (sum1 > width || sum2 > width || sum3 > width || sum4 > width) {
            return false;
        }
        if (index == -1) {
            if (sum1 == width || sum2 == width || sum3 == width || sum4 == width) {
                return true;
            }
            else {
                return false;
            }
        }
        
        return dfs(nums, index-1, sum1+nums[index], sum2, sum3, sum4, width) || 
                dfs(nums, index-1, sum1, sum2+nums[index], sum3, sum4, width) ||
                dfs(nums, index-1, sum1, sum2, sum3+nums[index], sum4, width) ||
                dfs(nums, index-1, sum1, sum2, sum3, sum4+nums[index], width);
    }
}
