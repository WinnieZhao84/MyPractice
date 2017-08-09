package LeetCode.Medium;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.

 * @author WinnieZhao
 *
 */
public class JumpGame {

    /**
        public class Solution {
            public boolean canJumpFromPosition(int position, int[] nums) {
                if (position == nums.length - 1) {
                    return true;
                }

                int furthestJump = Math.min(position + nums[position], nums.length - 1);
                for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
                    if (canJumpFromPosition(nextPosition, nums)) {
                        return true;
                    }
                }

                return false;
            }

            public boolean canJump(int[] nums) {
                return canJumpFromPosition(0, nums);
            }
        }
    **/
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i=0; i<nums.length; ++i) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
    
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        int[] nums = {2,3,1,1,4};
        System.out.println(solution.canJump(nums));
        
        int[] nums1 = {0,2,3};
        System.out.println(solution.canJump(nums1));
        
        int[] nums2 = {3,2,1,0,4};
        System.out.println(solution.canJump(nums2));
    }
}
