package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]

 * @author WinnieZhao
 *
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        
        if (nums == null || nums.length == 1) {
            return res;
        }
        if (nums.length == 1) {
            res.add(nums[0]);
            return res;
        }
        
        int len = nums.length;
        int[] count = new int[len];
        int[] pre = new int[len];
        
        Arrays.sort(nums);
        int maxCount = 0;
        int maxIndex = -1;
        for (int i=0; i<=nums.length-1; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j=i-1; j>=0; j--) {
                if (nums[i] % nums[j] == 0 && count[i] < count[j] + 1) {
                    count[i] = count[j] + 1;
                    pre[i] = j;
                }
            }
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxIndex = i;
            }
        }
        
        while (maxIndex != -1) {
            res.add(nums[maxIndex]);
            maxIndex = pre[maxIndex];
        }
        return res;
    }
    
    public static void main(String[] args) {
        LargestDivisibleSubset solution = new LargestDivisibleSubset();
        int[] nums = {1,2,5,10,20};
        System.out.println(solution.largestDivisibleSubset(nums));
    }
}
