package LeetCode.Hard;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.

 * Created by WinnieZhao on 2017/4/26.
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int[] nums = new int[ratings.length];

        nums[0] = 1;
        for (int i=1; i< ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                nums[i] = nums[i-1] + 1;
            }
            else {
                nums[i] = 1;
            }
        }

        for (int i= ratings.length-1; i>=1; i--) {
            if (ratings[i-1] > ratings[i]) {
                nums[i-1] = Math.max(nums[i] + 1, nums[i-1]);
            }
        }

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        return total;
    }
}
