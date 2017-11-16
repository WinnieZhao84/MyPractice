package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * 
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * @author WinnieZhao
 *
 */
public class SearchForARange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        if (nums == null ||  nums.length == 0 || (nums.length == 1 && target != nums[0])) {
            return res;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                int i = mid;
                int j = mid;
                while (i >=0 && nums[i] == target) {
                    i--;
                }
                while (j<=nums.length-1 && nums[j] == target) {
                    j++;
                }
                res[0] = i+1;
                res[1] = j-1;
                return res;
            }
            else {
                if (target > nums[mid]) {
                    start = mid + 1;
                }
                else if (target < nums[mid]) {
                    end = mid - 1;
                }
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        SearchForARange solution = new SearchForARange();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] nums1 = {1, 3};
        
        System.out.println(Arrays.toString(solution.searchRange(nums, 8)));
        System.out.println(Arrays.toString(solution.searchRange(nums1, 1)));
    }
}
