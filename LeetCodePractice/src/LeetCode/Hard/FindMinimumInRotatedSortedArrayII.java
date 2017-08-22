package LeetCode.Hard;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element. The array may contain duplicates.

 * Created by WinnieZhao on 4/25/2017.
 */
public class FindMinimumInRotatedSortedArrayII {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        /**
         * {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2} and {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2}，
         * So if the first and last and the middle are all the same value, binary search won't work because it can't decide which half should go to
         * so in this case, we move the end--, skip one same num, it won't affect the final result because we just removed one duplicate num.
         * And then still use binary search for the remaining nums. In the worst case such as all the nums are the same value, the complexity will be O(n)
         *
         */
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                // if mid equals to end, that means it's fine to remove end
                // the smallest element won't be removed
                end--;
            }
            else if (nums[mid] < nums[end]) {
                end = mid;
                // of course you can merge == & <
            }
            else {
                start = mid;
                // or start = mid + 1
            }
        }

        if (nums[start] <= nums[end]) {
            return nums[start];
        }
        return nums[end];
    }
}
