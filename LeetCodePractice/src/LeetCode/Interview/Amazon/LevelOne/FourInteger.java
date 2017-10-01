package LeetCode.Interview.Amazon.LevelOne;

import java.util.Arrays;

/**
 * Given four integers, make F(S) = abs(S[0]-S[1])+abs(S[1]-S[2])+abs(S[2]-S[3]) to be largest.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class FourInteger {

    public static int[] fourInteger(int A, int B, int C, int D) {
        int[] nums = new int[]{A, B, C, D};
        Arrays.sort(nums);
        swap(nums, 0, 1);
        swap(nums, 2, 3);
        swap(nums, 0, 3);
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
