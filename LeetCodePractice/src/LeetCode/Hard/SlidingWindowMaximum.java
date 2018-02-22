package LeetCode.Hard;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
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
 *  Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 * Created by WinnieZhao on 7/3/2017.
 */
public class SlidingWindowMaximum {

    /**
     * We scan the array from 0 to n-1, keep "promising" elements in the deque.
     * The algorithm is amortized O(n) as each element is put and polled once.
     *
     * At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window.
     * This means:
     * 1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head,
     *    as we are using a deque and elements are ordered as the sequence in the array
     * 2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail.
     *    This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i],
     *    or any other subsequent window: a[i] would always be a better candidate.
     * 3. As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the
     *    deque is the max element in [i-(k-1),i]

     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];

        for(int i = 0; i < nums.length; i++){
            // Remove smaller numbers in k range as they are useless
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }

            // Remove numbers out of range k
            while (!queue.isEmpty() && i - queue.peek() + 1 > k) {
                queue.poll();
            }

            queue.offer(i);

            // The first item in the deque will be the greatest
            if (i-k + 1 >=0) {
                res[i - k + 1] = nums[queue.peek()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();

        int[] nums = {1,3,-1,-3,5,3,6,7};

        int[] res = solution.maxSlidingWindow(nums, 3);

        Arrays.stream(res).forEach(e -> System.out.println(e));
    }
}
