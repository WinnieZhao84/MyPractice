package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
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
        int length = numbers.length;
        
        for (int i=0; i<length; i++) {
            int newTarget = target - numbers[i];
            index[0] = i+1;
            
            int result = this.binarySearch(0, length-1, newTarget, numbers, i);
            if (result == -1) {
                continue;
            }
            else {
                index[1] = result + 1;
                return index;
            }
        }
        
        return null;
    }
    
    private int binarySearch(int start, int end, int target, int[] numbers, int firstIndex) {
        if (start > end) {
            return -1;
        }
        
        int middle = (end+start) / 2;
        if (numbers[middle] == target) {
            if (firstIndex == middle) {
                return this.binarySearch(middle+1, end, target, numbers, firstIndex);
            }
            return middle;
        }
        else if (numbers[middle] > target) {
            return this.binarySearch(start, middle-1, target, numbers, firstIndex);
        }
        else if (numbers[middle] < target) {
            return this.binarySearch(middle+1, end, target, numbers, firstIndex);
        }
        return -1;
    }
    
    public static void main(String args[]) {
        TwoSum solution = new TwoSum();
        
        int[] numbers = {1,2,3,4,4,9,56,90};
        System.out.println(Arrays.toString(solution.twoSum_Better(numbers, 8)));
                
    }
}
