package LeetCode.Medium;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 * 
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.

 * @author WinnieZhao
 *
 */
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // if n = 10, mid = 5;
        int start = 1;
        int end = nums.length-1;
        int mid = 0;
        while (start < end) {
            mid = start + (end - start)/2;
            
            int count = 0;
            for (int n : nums) {
                if (n <= mid) {
                    count++;
                }
            }
            
            if (count <= mid) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        
        return start;
    }
    
}
