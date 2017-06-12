package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 *
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 *
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].

 * Created by WinnieZhao on 6/12/2017.
 */
public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;
        for (int i=0; i<nums.length-2; i++) {
            int k = i + 2;
            for (int j=i+1; j<nums.length-1&& nums[i] != 0; j++) {

                while (k<nums.length && nums[i]+nums[j] > nums[k]) {
                    k++;
                }
                /**
                 * if we are able to find this right limit value of k(indicating the element just greater than
                 * nums[i] + nums[j], we can conclude that all the elements in nums array in the range
                 * (j+1, k-1) (both included) satisfy the required inequality. Thus, the count of elements
                 * satisfying the inequality will be given by (k-1) - (j+1) + 1 = k - j - 1
                 */
                count += k-j-1;
            }
        }

        return count;
    }
}
