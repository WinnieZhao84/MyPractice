package LeetCode.Easy;

/** 
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, the only constraint 
 * stopping you from robbing each of them is that adjacent houses have 
 * security system connected and it will automatically contact the police 
 * if two adjacent houses were broken into on the same night.
 * 
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.

 * @author ASUS-PC
 *
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i=1; i<len; i++) {
            if (i==1) {
                dp[i] = Math.max(nums[i-1], nums[i]);
            }
            else {
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            }
        }

        return dp[len-1];
    }

    public int rob_better(int[] num) {
        int rob = 0; //max money can get if rob current house
        int notRob = 0; //max money can get if not rob current house

        for(int i=0; i<num.length; i++) {
            int curRob = notRob + num[i]; //if rob current value, previous house must not be robbed
            notRob = Math.max(notRob, rob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
            rob = curRob;
        }
        return Math.max(rob, notRob);
    }
}
