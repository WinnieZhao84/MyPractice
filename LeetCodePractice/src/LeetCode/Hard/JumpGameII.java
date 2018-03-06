package LeetCode.Hard;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * For example:
 *
 * Given array A = [2,3,1,1,4]
 *
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *
 * Note: You can assume that you can always reach the last index.

 * Created by WinnieZhao on 2017/6/26.
 */
public class JumpGameII {
    public int jump(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        /**
         * The main idea is based on greedy. Let’s say the range of the current jump is [curBegin, curEnd],
         * curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
         * Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest,
         * then keep the above steps, as the following:
         *
         */
        int jumps = 0, curEnd = 0, curFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            curFarthest = Math.max(curFarthest, i + nums[i]);

            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;

                if (curEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2,3,1,1,4};
        System.out.println(solution.jump(nums));
    }
}

