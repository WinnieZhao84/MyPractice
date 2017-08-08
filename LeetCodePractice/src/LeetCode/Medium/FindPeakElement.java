package LeetCode.Medium;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] != num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 * @author WinnieZhao
 *
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;

        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    
    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();
        
        int[] nums = {1,2};
        System.out.println(solution.findPeakElement(nums));
    }
}
