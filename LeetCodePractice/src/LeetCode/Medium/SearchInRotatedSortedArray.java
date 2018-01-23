package LeetCode.Medium;


/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

 * @author WinnieZhao
 *
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length-1;

        if (nums[start] == nums[end]) {
            return nums[start] == target ? start : -1;
        }

        while (start < end) {
            int mid = start + (end - start)/2;

            if (nums[mid] == target) {
                return mid;
            }
            else {
                // left side sorted 4 5 6 7 0 1 2
                if (nums[mid] >= nums[start]) {
                    if (nums[mid] > target && nums[start] <= target) {
                        end = mid-1;
                    }
                    else {
                        start = mid+1;
                    }
                }
                // right side sorted 7,0,1,2,3,4,5
                else {
                    if (nums[mid] < target && nums[end] >= target) {
                        start = mid+1;
                    }
                    else {
                        end = mid-1;
                    }
                }
            }
        }

        if (start >= end && nums[start] != target) {
            return -1;
        }

        return start;
    }
    
    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        
        int[] nums = {3, 1};
        
        System.out.print(solution.search(nums, 1));
    }
}
