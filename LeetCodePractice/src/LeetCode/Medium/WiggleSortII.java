package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * 
 * Note: You may assume all input has valid answer.
 * 
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?

 * @author WinnieZhao
 *
 */
public class WiggleSortII {

    // Read the discussion, google's interview question which requires either O(n) time or O(1) space
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        Arrays.sort(nums);
        
        int start = 0;
        int end = nums.length - 1;
        int middle = start + (end - start) / 2;
        
        int[] temp = Arrays.copyOf(nums, nums.length);
        
        int i=middle;
        int j=end;
        for (int k=0; k<=end; k++) {
            if (k%2 == 0) {
                nums[k] = temp[i];
                i--;
            }
            else {
                nums[k] = temp[j];
                j--;
            }

            if (j == middle) {
                break;
            }
        }
        if (i==0) {
            nums[end] = temp[0];
        }
        
    }
    
    public static void main(String[] args) {
        WiggleSortII solution = new WiggleSortII();
        
        int[] nums = {1,1,2,1,2,2,1};
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
        
        
        int[] nums1 = {1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums1);
        System.out.println(Arrays.toString(nums1));
        
        int[] nums2 = {4, 5, 5, 6};
        solution.wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
