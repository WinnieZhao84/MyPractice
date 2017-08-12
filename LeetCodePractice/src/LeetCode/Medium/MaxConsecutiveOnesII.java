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

    /**
     * 说我们有一次将0翻转成1的机会，问此时最大连续1的个数，再看看follow up中的说明，很明显是让我们只遍历一次数组，
     * 肯定需要用一个变量cnt来记录连续1的个数吧，那么当遇到了0的时候怎么处理呢，因为我们有一次0变1的机会，
     * 所以我们遇到0了还是要累加cnt，然后我们此时需要用另外一个变量cur来保存当前cnt的值，然后cnt重置为0，
     * 以便于让cnt一直用来统计纯连续1的个数，然后我们每次都用用cnt+cur来更新结果res
     *
     * @param nums
     * @return
     */
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
