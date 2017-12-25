package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
 * must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * 
 * Output: index1=1, index2=2
 * 
 * @author WinnieZhao
 *
 */
public class TwoSum {
    // Time Limit Exceeded
    public int[] twoSum(int[] numbers, int target) {
        
        int[] index = new int[2];
        int length = numbers.length;
        for (int i=0; i<length; i++) {
            int newTarget = target - numbers[i];
            index[0] = i+1;
            
            for (int j=0; j<length; j++) {
                if (i==j) continue;
                
                if (numbers[j] == newTarget) {
                    index[1] = j+1;
                    return index;
                }
            }
        }
        
        return null;
    }
    
    // Use binary search to improve the performance
    public int[] twoSum_Better(int[] numbers, int target) {
        int[] index = new int[2];

        if (numbers == null || numbers.length == 0) {
            return index;
        }

        int left=0;
        int right=numbers.length-1;

        while (left<right) {

            if (numbers[left] + numbers[right] == target) {
                index[0] = left+1;
                index[1] = right+1;

                return index;
            }
            else if (numbers[left] + numbers[right] < target) {
                left++;
            }
            else {
                right--;
            }
        }

        return index;
    }

    
    public static void main(String args[]) {
        TwoSum solution = new TwoSum();
        
        int[] numbers = {1,2,3,4,4,9,56,90};
        System.out.println(Arrays.toString(solution.twoSum_Better(numbers, 8)));
                
    }
}
