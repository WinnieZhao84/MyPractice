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
            return nums[0] == target ? true : false;
        }

        int start = 0;
        int end = nums.length-1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return true;
            }
            else {
                // Eg: 2,4,5,6,7,0,1
                if (nums[mid] > nums[start]) {
                    // Left is sorted
                    if (target < nums[mid] && target >= nums[start]) {
                        end = mid-1;
                    }
                    else {
                        start = mid + 1;
                    }
                }
                // Eg: 6,7,0,1,2,4,5
                else if (nums[mid] < nums[start]) {
                    // Right is sorted
                    if (target > nums[mid] && target <= nums[end]) {
                        start = mid + 1;
                    }
                    else {
                        end = mid - 1;
                    }
                }
                // have no idea about the array, but we can exclude nums[start] because nums[start] == nums[mid]
                else {
                    start++;
                }
            }
        }

        if (start >= end && target != nums[start]) {
            return false;
        }

        return true;

    }
    
    public static void main(String[] args) {
        int[] nums = {1,3};
        
        SearchInRotatedSortedArrayII solution = new SearchInRotatedSortedArrayII();
        System.out.println(solution.search(nums, 3));
    }
}
