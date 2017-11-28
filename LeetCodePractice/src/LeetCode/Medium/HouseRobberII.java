package LeetCode.Medium;

/**
 * Note: This is an extension of House Robber.
 * 
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
 * This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system 
 * for these houses remain the same as for those in the previous street.
 * 
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 * 
 * @author WinnieZhao
 *
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        
        if (nums == null || nums.length ==0 ) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(this.rob(nums, 0, nums.length-2), this.rob(nums, 1, nums.length-1));
        
    }
    
    private int rob(int[] nums, int start, int end) {

        int preNotRob=0;
        int preRob=0;

        int notRob=0;
        int rob=0;

        for (int i=start; i<=end; i++) {
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);

            preNotRob = notRob;
            preRob = rob;
        }

        return Math.max(rob, notRob);
    }
    
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        
        HouseRobberII solution = new HouseRobberII();
        System.out.println(solution.rob(nums));
    }
    
}
