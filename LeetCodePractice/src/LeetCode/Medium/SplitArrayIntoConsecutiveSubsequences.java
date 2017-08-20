package LeetCode.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * You are given an integer array sorted in ascending order (may contain duplicates),
 * you need to split them into several subsequences, where each subsequences consist
 * of at least 3 consecutive integers. Return whether you can make such a split.
 *
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation: You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 *
 * Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation: You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 *
 * Created by WinnieZhao on 8/19/2017.
 */
public class SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // We iterate through the array once more and for each element we either see if it can be
        // appended to a previously constructed consecutive sequence or if it can be the start of
        // a new consecutive sequence. If neither are true, then we return false.
        Map<Integer, Integer> appendFreq = new HashMap<>();
        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }
            if (appendFreq.getOrDefault(num,0) > 0) {
                appendFreq.put(num, appendFreq.get(num) - 1);
                appendFreq.put(num+1, appendFreq.getOrDefault(num+1,0) + 1);
            }
            else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num+1, freq.get(num+1) - 1);
                freq.put(num+2, freq.get(num+2) - 1);
                appendFreq.put(num+3, appendFreq.getOrDefault(num+3,0) + 1);
            }
            else {
                return false;
            }
            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSubsequences solution = new SplitArrayIntoConsecutiveSubsequences();

        int[] array = {1,2,3,5};

        System.out.println(solution.isPossible(array));
    }
}
