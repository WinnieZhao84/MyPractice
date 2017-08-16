package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?

 * @author WinnieZhao
 *
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int redCount=0;
        int whiteCount=0;
        int blueCount=0;
        
        for (int n : nums) {
            if (n==0) {
                redCount++;
            }
            else if (n==1) {
                whiteCount++;
            }
            else if (n==2) {
                blueCount++;
            }
        }
        
        int length = nums.length;
        int i=0;
        int red=0;
        int white=0;
        int blue=0;
        while (i<length) {
            while (red < redCount) {
                nums[i++] = 0;
                red++;
            }
            while (white < whiteCount) {
                nums[i++] = 1;
                white++;
            }
            while (blue < blueCount) {
                nums[i++] = 2;
                blue++;
            }
        }
    }
    
    // Insert Sort
    public void sortColors_onePass(int[] nums) {
        int length = nums.length;
        
        for (int i=1; i< length; i++) {
            int currentValue = nums[i];
            int position = i;
            
            for (int j=i-1; j>=0; j--) {
                if (nums[j] > currentValue) {
                    nums[j+1] = nums[j];
                    position--;
                }
                else {
                    break;
                }
            }
            nums[position] = currentValue;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {0,0};
        SortColors solution = new SortColors();
        
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
