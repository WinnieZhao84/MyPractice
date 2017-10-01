package LeetCode.Interview.Amazon.LevelOne;

import java.util.LinkedList;

/**
 * Created by WinnieZhao on 9/30/2017.
 * Same as Leetcode
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *  Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i=0; i<nums.length; i++) {

            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }

            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offerLast(i);

            if (i >= k - 1) {
                res[i-k+1] = nums[queue.peekFirst()];
            }

        }

        return res;

    }
}
