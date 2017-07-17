package LeetCode.Hard;

/**
 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that
 * has the maximum average value. And you need to output the maximum average value.
 *
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 *
 * Explanation:
 * when length is 5, maximum average value is 10.8,
 * when length is 6, maximum average value is 9.16667.
 * Thus return 12.75.
 *
 * Note:
 * 1 <= k <= n <= 10,000.
 * Elements of the given array will be in range [-10,000, 10,000].
 * The answer with the calculation error less than 10-5 will be accepted.

 * Created by WinnieZhao on 2017/7/16.
 */
public class MaximumAverageSubarrayII {

    /**
     * To understand the idea behind this method, let's look at the following points.
     * Firstly, we know that the value of the average could lie between the range (min, max)(min,max). Here, min and max
     * refer to the minimum and the maximum values out of the given nums array. This is because, the average can't
     * be lesser than the minimum value and can't be larger than the maximum value.
     *
     * But, in this case, we need to find the maximum average of a subarray with at least k elements.
     * The idea in this method is to try to approximate(guess) the solution and to try to find if this solution really exists.
     * If it exists, we can continue trying to approximate the solution even to a further precise value,
     * but choosing a larger number as the next approximation. But, if the initial guess is wrong, and the initial
     * maximum average value(guessed) isn't possible, we need to try with a smaller number as the next approximate.
     *
     * Now, instead of doing the guesses randomly, we can make use of Binary Search. With min and max as the initial
     * numbers to begin with, we can find out the mid of these two numbers given by (min+max)/2.
     * Now, we need to find if a subarray with length greater than or equal to k is possible with an average sum
     * greater than this mid value.
     *
     *   (nums[i] + nums[i+1] +...+ nums[j]) / (j-i+1) > x
     * => nums[i] + nums[i+1] +...+ nums[j] > x*(j-i+1)
     * =>(nums[i]-x) + (nums[i+1]-x) +... + (nums[j]-x) > 0

     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length <k || k<=0) {
            return Integer.MIN_VALUE;
        }

        double min = nums[0], max = nums[0];

        for(int i=0; i<nums.length; i++){
            if (nums[i]<min) min = nums[i];
            if (nums[i]>max) max = nums[i];
        }

        while (min <= max) { //binary search to find the max average between min and max
            double mid = min +(max-min)/2.0;
            if (hasAvgAbove(nums, k, mid)) {
                min = mid + 0.000_005; //error less than 10^-5 will be accepted.
            }
            else {
                max = mid - 0.000_005;
            }
        }
        return max;
    }

    private boolean hasAvgAbove(int[] nums, int k, double target) {
        double sum = 0, extraSum =0;
        for(int i=0; i<k; i++){
            sum += nums[i]-target;
        }
        // must have at least k elements; the nums before the last k element should be kept if their sum > 0;
        // else we should remove them from the current sum (equivalent to update the start position)
        int curr = k;
        while (curr < nums.length) {
            if (sum >= 0) {
                return true;
            }
            sum += nums[curr] - target;
            extraSum += nums[curr-k] - target;

            if (extraSum < 0) { //update the start position of the current sum
                sum -= extraSum;
                extraSum = 0;
            }
            curr++;
        }
        return sum >= 0;
    }
}
