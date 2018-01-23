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
        if (nums == null || nums.length == 0) {
            return;
        }
        int num0 = 0, num1 = 0, num2 = 0;

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) ++num0;
            else if (nums[i] == 1) ++num1;
            else if (nums[i] == 2) ++num2;
        }

        for(int i = 0; i < num0; ++i) nums[i] = 0;
        for(int i = 0; i < num1; ++i) nums[num0+i] = 1;
        for(int i = 0; i < num2; ++i) nums[num0+num1+i] = 2;
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

    // One pass solution
    public void sortColors_onePass2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int m=0;
        int n=nums.length-1;

        for (int i=0; i<=n; i++) {
            if (nums[i] == 0) {
                this.swap(nums, m++, i);
            }
            else if (nums[i] == 2) {
                this.swap(nums, n--, i--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }
    
    public static void main(String[] args) {
        int[] nums = {0,0};
        SortColors solution = new SortColors();
        
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
