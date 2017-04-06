package LeetCode.Medium;

/**
 * 487
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 *
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 *
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 *
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 *
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream? In other words,
 * you can't store all numbers coming from the stream as it's too large to hold in memory.
 * Could you solve it efficiently?

 * Created by WinnieZhao on 2017/4/5.
 */
public class MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, cur = 0, cnt = 0;
        for (int num : nums) {
            ++cnt;
            if (num == 0) {
                cur = cnt;
                cnt = 0;
            }
            res = Math.max(res, cnt + cur);
        }
        return res;
    }
}
