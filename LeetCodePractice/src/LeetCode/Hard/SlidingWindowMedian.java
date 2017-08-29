package LeetCode.Hard;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 *
 *  Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 *  Note:
 *  You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

 * Created by WinnieZhao on 2017/7/12.
 */
public class SlidingWindowMedian {

    /**
     * Use two Heaps to store numbers. high for numbers greater than current median, low for numbers smaller than and
     * equal to current median.
     * Low queue count should be always keeping 1 more than high, so the peek() from low will be the median num
     * Keep adding number from the right side of the sliding window and remove number from left side of the sliding window.
     * And keep adding current median to the result.
     *
     */
    PriorityQueue<Double> high = new PriorityQueue();
    PriorityQueue<Double> low = new PriorityQueue(Collections.reverseOrder());

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        int index = 0;

        for (int i=0; i<nums.length; i++) {
            if (i >= k) {
                remove(nums[i-k]);
            }

            add((double)nums[i]);

            if (i >= k-1) {
                res[index++] = findMedian();
            }
        }
        return res;
    }

    // Always move the greater value from low queue to high queue
    private void add(double num) {
        low.offer(num);
        high.offer(low.poll());
        if (low.size() < high.size()) {
            low.offer(high.poll());
        }
    }

    private void remove(double num) {
        if (num <= findMedian()) {
            low.remove(num);
        }
        else {
            high.remove(num);
        }
        if (low.size() < high.size()) {
            low.offer(high.poll());
        }
        else if (low.size() > high.size()+1) {
            high.offer(low.poll());
        }
    }

    private double findMedian() {
        if (low.size() == high.size()) {
            return (low.peek() + high.peek()) / 2.0;
        }
        else {
            return low.peek();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian solution = new SlidingWindowMedian();

        int[] nums = {1,3,-1,-3,5,3,6,7};

        solution.medianSlidingWindow(nums, 3);
    }

}
