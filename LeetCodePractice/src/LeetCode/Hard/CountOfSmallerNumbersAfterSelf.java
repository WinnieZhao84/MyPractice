package LeetCode.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller
 * elements to the right of nums[i].
 *
 * Example: Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Return the array [2, 1, 1, 0].

 * Created by WinnieZhao on 6/5/2017.
 */
public class CountOfSmallerNumbersAfterSelf {

    /**
     * Use binary search to locate the index, which cost O(logn),
     * then use O(n) to move the array, so O(logn) can be ignored,
     * there are totally n elements, so runtime will be O(n^2).
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];

        List<Integer> sorted = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            result[i] = index;
            sorted.add(index, nums[i]);
        }

        return Arrays.asList(result);
    }

    // Binary search
    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0) return 0;

        int start = 0;
        int end = sorted.size() - 1;

        if (sorted.get(end) < target) {
            return end + 1;
        }

        if (sorted.get(start) >= target) {
            return 0;
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (sorted.get(mid) < target) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }

        if (sorted.get(start) >= target) {
            return start;
        }

        return end;
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();

        int[] nums = {5, 2, 6, 1};
        System.out.print(solution.countSmaller(nums).toString());
    }
}
