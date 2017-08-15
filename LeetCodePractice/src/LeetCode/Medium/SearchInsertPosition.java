package LeetCode.Medium;

/**
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * 
 * You may assume no duplicates in the array. Here are few examples.
 * 
 * [1,3,5,6], 5 => 2
 * [1,3,5,6], 2 => 1
 * [1,3,5,6], 7 => 4
 * [1,3,5,6], 0 => 0

 * @author WinnieZhao
 *
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        
        if (nums == null || nums.length == 0) return 0;
        
        if (target <= nums[0]) {
            return 0;
        }
        else if (target > nums[nums.length-1]) {
            return nums.length;
        }
        else if (target == nums[nums.length-1]) {
            return nums.length-1;
        }
        
        int res = 0;
        int start = 0;
        int end = nums.length-1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < target) {
                if (nums[mid+1] > target) {
                    res = mid+1;
                    break;
                }
                start = mid + 1;
            }
            else if (nums[mid] > target) {
                if (nums[mid-1] < target) {
                    res = mid;
                    break;
                }
                end = mid - 1;
            }
            else {
                res = mid;
                break;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();
        
        int[] nums = {1,3};
        int[] nums1 = {1,3,5,6};
        int[] nums2 = {1,3,5,6};
        int[] nums3 = {1,3,5,6};
        int[] nums4 = {1,3,5,6};
        
        System.out.println(solution.searchInsert(nums, 2));
        System.out.println(solution.searchInsert(nums1, 5));
        System.out.println(solution.searchInsert(nums2, 2));
        System.out.println(solution.searchInsert(nums3, 7));
        System.out.println(solution.searchInsert(nums4, 0));
        
    }
}
