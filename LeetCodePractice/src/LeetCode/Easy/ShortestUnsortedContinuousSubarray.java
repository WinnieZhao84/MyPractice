package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 *
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 *
 * Created by WinnieZhao on 2017/5/15.
 */
public class ShortestUnsortedContinuousSubarray {

    // O(nlgn) runtime
    public int findUnsortedSubarray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result  = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int l = 0, r = copy.length - 1;
        while(l < r) {
            if(copy[l] == nums[l]) l++;
            if(copy[r] == nums[r]) r--;
            if(copy[l] != nums[l] && copy[r] != nums[r]) break;
        }
        result = r - l + 1;
        if(l == r) result = 0;
        return result;

    }

    // O(n) runtime, O(1) space
    public int findUnsortedSubarray_better(int[] nums) {
        int len=nums.length;
        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        int start=-1, end=-1;

        for(int i=0; i<len; i++){
            max = Math.max(max, nums[i]); //from left to right, search the current max
            min = Math.min(min, nums[len-i-1]);  //from right to left, search the current min

            if(nums[i] < max)
                end = i;
            if(nums[len-i-1] > min)
                start = len-i-1;
        }

        if(start==-1) //the entire array is already sorted
            return 0;

        return end-start+1;
    }
}
