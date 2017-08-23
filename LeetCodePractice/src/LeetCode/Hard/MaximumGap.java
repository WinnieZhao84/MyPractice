package LeetCode.Hard;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *
 * Try to solve it in linear time/space. Return 0 if the array contains less than 2 elements.
 *
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 *
 * For example: [5,9,8,3,15]
 * Output: Sorted array: [3,5,8,9,15], the maximum gap is 15-9=6

 * Created by WinnieZhao on 2017/6/19.
 */
public class MaximumGap {

    /**
     * Suppose there are N elements and they range from A to B.
     * Then the maximum gap will be no smaller than ceiling[(B - A) / (N - 1)]
     *
     * Let the length of a bucket to be len = ceiling[(B - A) / (N - 1)], then we will have at most num = (B - A) / len + 1 of bucket
     * for any number K in the array, we can easily find out which bucket it belongs by calculating loc = (K - A) / len and
     * therefore maintain the maximum and minimum elements in each bucket.
     *
     * Since the maximum difference between elements in the same buckets will be at most len - 1,
     * so the final answer will not be taken from two elements in the same buckets.
     *
     * For each non-empty buckets p, find the next non-empty buckets q, then q.min - p.max could be the potential answer to the question.
     * Return the maximum of all those values.
     *
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // the minimum possible gap, ceiling of the integer division
        int gap = (int) Math.ceil((double)(max - min)/(nums.length - 1));

        int[] bucketsMIN = new int[nums.length - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[nums.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);

        // put numbers into buckets
        for (int num : nums) {
            if (num == min || num == max)
                continue;

            int index = (num-min)/gap; // index of the right position in the buckets
            bucketsMIN[index] = Math.min(num, bucketsMIN[index]);
            bucketsMAX[index] = Math.max(num, bucketsMAX[index]);
        }

        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i=0; i<nums.length-1; i++) {

            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;

            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i]-previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }

        maxGap = Math.max(maxGap, max - previous); // update the final max value gap
        return maxGap;
    }
}
