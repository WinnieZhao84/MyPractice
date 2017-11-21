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

    /**
     * Time complexity : O(n). We traverse the nums array of size nn once only.
     */
    public class Solution {
        public int findPeakElement(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1])
                    return i;
            }
            return nums.length - 1;
        }
    }

    /**
     * Time complexity : O(log(n))
     * Space complexity : O(1)
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    /**
     * Time complexity : O(log(n))
     * We reduce the search space in half at every step. Thus, the total search space will be consumed in log(n) steps.
     * Here, nn refers to the size of nums array.
     * Space complexity : O(log(n))
     * We reduce the search space in half at every step. Thus, the total search space will be consumed in log(n) steps.
     * Thus, the depth of recursion tree will go up to log(n)
     *
     * @param nums
     * @return
     */
    public int findPeakElement_Recursive(int[] nums) {
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
