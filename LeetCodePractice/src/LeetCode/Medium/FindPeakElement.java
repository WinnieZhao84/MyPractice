package LeetCode.Medium;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ¡Ù num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -¡Þ.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 * @author WinnieZhao
 *
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int start = 0;
        int end = nums.length-1;
        
        int index = this.findPeakElementHelper(nums, start, end);
        
        return index;
    }
    
    private int findPeakElementHelper(int[] nums, int start, int end) {
        if (start == 0 && start == end) {
            if (nums[start] > nums[start+1]) {
                return start;
            }
            else {
                return -1;
            }
        }
        
        if (start == nums.length-1 && start == end) {
            if (nums[start] > nums[start-1]) {
                return start;
            }
            else {
                return -1;
            }
        }
        while (start <= end) {
            int mid = start + (end - start)/2;
            
            if (mid == 0) {
                if (nums[mid] > nums[mid+1]) {
                    return mid;
                }
                else {
                    return this.findPeakElementHelper(nums, mid+1, end);
                }
            }
            if (mid == nums.length-1) {
                if (nums[mid] > nums[mid-1]) {
                    return mid;
                }
                else {
                    return this.findPeakElementHelper(nums, start, mid-1);
                }
            }
            if (nums[mid-1] < nums[mid] && nums[mid+1] < nums[mid]) {
                return mid;
            }
            else {
                int idx1 = this.findPeakElementHelper(nums, start, mid-1);
                return idx1 >= 0 ? idx1 : this.findPeakElementHelper(nums, mid+1, end);
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();
        
        int[] nums = {1,2};
        System.out.println(solution.findPeakElement(nums));
    }
}
