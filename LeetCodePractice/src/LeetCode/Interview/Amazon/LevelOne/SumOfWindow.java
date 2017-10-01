package LeetCode.Interview.Amazon.LevelOne;

import java.util.ArrayList;
import java.util.List;

/**
 * [4, 2, 73, 11, -5] and window size 2 should return [6, 75, 84, 6].
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class SumOfWindow {

    public List<Integer> windowSum(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        int sum = 0;
        for (int i=0; i<k; i++) {
            sum += nums[i];
        }
        result.add(sum);

        for (int i=k; i<nums.length; i++) {
            sum += nums[i] - nums[i-k];
            result.add(sum);
        }

        return result;
    }
}
