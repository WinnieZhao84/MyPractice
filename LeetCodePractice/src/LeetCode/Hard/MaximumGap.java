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
     * Suppose there are N elements in the array, the min value is min and the max value is max.
     * Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
     *
     * Let gap = ceiling[(max - min ) / (N - 1)]. We divide all numbers in the array into n-1 buckets,
     * where k-th bucket contains all numbers in [min + (k-1)gap, min + k*gap). Since there are n-2 numbers
     * that are not equal min or max and there are n-1 buckets, at least one of the buckets are empty.
     * We only need to store the largest number and the smallest number in each bucket.

     * @param nums
     * @return
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

        maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
        return maxGap;
    }
}
