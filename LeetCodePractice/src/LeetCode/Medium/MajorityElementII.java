package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than n/3 times.
 * The algorithm should run in linear time and in O(1) space.
 * 
 * @author WinnieZhao
 *
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        List<Integer> result = new ArrayList<>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1) {
                count1++;
            }
            else if (nums[i] == number2) {
                count2++;
            }
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } 
            else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } 
            else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        
        return result;
    }
    
    public static void main(String[] args) {
        MajorityElementII solution = new MajorityElementII();
        int[] nums = {3,3,3,4,4,4,5,5,5};
        System.out.println(solution.majorityElement(nums));
    }
}
