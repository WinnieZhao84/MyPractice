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
        
        if (nums == null || nums.length  == 0 || target < nums[0] || target > nums[nums.length-1]) {
            return res;
        }
        else if (nums[0] == target && nums[0] == nums[nums.length-1]) {
            res[0] = 0;
            res[1] = nums.length-1;
            return res;
        }
        else {
            int start = 0;
            int end = nums.length;
            while (start <= end) {
                int mid = start + (end- start) / 2;
                
                if (nums[mid] > target) {
                    end = mid - 1;
                }
                else if (nums[mid] < target) {
                    start = mid + 1;
                }
                else {
                    start = mid - 1;
                    end = mid + 1;
                    res[0] = mid;
                    res[1] = mid;
                    while (start >= 0) {
                        if (nums[start] == target) {
                            res[0] = start;
                            start--;
                        }
                        else {
                            break;
                        }

                    }
                    while (end <= nums.length-1) {
                        if (nums[end] == target) {
                            res[1] = end;
                            end++;
                        }
                        else {
                            break;
                        }
                    }
                    break;
                }
                if (start > end) {
                    break;
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
