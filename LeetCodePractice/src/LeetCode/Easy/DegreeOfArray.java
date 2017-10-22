package LeetCode.Easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined
 * as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums,
 * that has the same degree as nums.
 *
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 *
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.

 * Created by WinnieZhao on 10/21/2017.
 */
public class DegreeOfArray {

    public int findShortestSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> frequencies = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            frequencies.put(num, frequencies.getOrDefault(num, new ArrayList<>()));
            frequencies.get(num).add(i);
        }

        int minLength = Integer.MAX_VALUE;
        int maxFreq = 1;
        for (int num : frequencies.keySet()) {
            maxFreq = Math.max(frequencies.get(num).size(), maxFreq);
        }

        for (int num : frequencies.keySet()) {
            if (frequencies.get(num).size() == maxFreq) {
                maxFreq = frequencies.get(num).size();

                int left = frequencies.get(num).get(0);
                int right = frequencies.get(num).get(frequencies.get(num).size()-1);
                minLength = Math.min(minLength, right - left + 1);
            }
        }

        return minLength;
    }

    public static void main(String[] args) {
        DegreeOfArray solution = new DegreeOfArray();

        int[] nums = {1,2,2,3,1,4,2};
        System.out.println(solution.findShortestSubArray(nums));
    }
}
