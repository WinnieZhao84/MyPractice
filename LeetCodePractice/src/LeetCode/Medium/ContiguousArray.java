package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/** 
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * 
 * Example 1:
 * Input: [0,1] 
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * 
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * 
 * Note: The length of the given binary array will not exceed 50,000.

 * @author WinnieZhao
 *
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int max = 0;
        Map<Integer, Integer> countDiffMap = new HashMap<>();
        countDiffMap.put(0, -1);

        int oneCnt=0;
        int zeroCnt=0;
        for (int i=0; i<nums.length; i++) {

            if (nums[i] == 0) {
                zeroCnt++;
            }
            else if (nums[i] == 1) {
                oneCnt++;
            }

            if (countDiffMap.containsKey(zeroCnt - oneCnt)) {
                max = Math.max(max, i - countDiffMap.get(zeroCnt - oneCnt));
            }
            else {
                countDiffMap.put(zeroCnt - oneCnt, i);
            }

        }

        return max;
    }
    
    public static void main(String[] args) {
        ContiguousArray solution = new ContiguousArray();
        
        int[] nums = {0, 0, 0, 1};
        System.out.println(solution.findMaxLength(nums));
    }
}
