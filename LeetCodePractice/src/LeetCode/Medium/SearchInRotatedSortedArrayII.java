package LeetCode.Medium;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed? 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.

 * @author WinnieZhao
 *
 */
public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end-start) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[start]) { // nums[start..mid] is sorted
                // check if target in left half
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            } 
            else if (nums[mid] < nums[start]) { // nums[mid..end] is sorted
                // check if target in right half
                if (target > nums[mid] && target < nums[start]) {
                    start = mid + 1;
                }
                else {
                    end = mid;
                }
            } 
            else { // have no idea about the array, but we can exclude nums[start] because nums[start] == nums[mid]
                start++;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,3};
        
        SearchInRotatedSortedArrayII solution = new SearchInRotatedSortedArrayII();
        System.out.println(solution.search(nums, 3));
    }
}
