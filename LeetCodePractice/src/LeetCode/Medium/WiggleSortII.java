package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * 
 * Note: You may assume all input has valid answer.
 * 
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?

 * @author WinnieZhao
 *
 */
public class WiggleSortII {

    // Read the discussion, google's interview question which requires either O(n) time or O(1) space
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int[] copy = new int[nums.length];
        for(int i = 0; i< nums.length; ++i) {
            copy[i] = nums[i];
        }

        int n = nums.length;
        int left = (n - 1) / 2;
        int right = n - 1;

        for(int i = 0; i< n; ++i) {
            int w = i & 1;

            // Take 2,   1,   0
            if(w == 0)
                nums[i] = copy[left--];
            // Take   5,   4,   3
            else
                nums[i] = copy[right--];
        }
        //Take in this order to avoid the case: [4,5,5,6]
        
    }

    /**
     * Using quick sort for O(n) solution.
     * Use a quick select algorithm to find out the median of the array, so the first half is less than the second half.
     *
     */
    static class Solution {
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }

            int n = nums.length;

            // Step 1: Find median of the array, return the index of the median
            int mid_index = this.findKthLargest(nums, (n+1)/2);
            int median = nums[mid_index];

            // Step 2: 3-way sort, put median in the middle,
            // numbers less than median on the left,
            // numbers greater than median on the right
            int s = 0, t = n - 1;
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                if (nums[i] < median) {
                    temp[s++] = nums[i];
                }
                else if (nums[i] > median) {
                    temp[t--] = nums[i];
                }
            }

            // add median into the middle
            while (s < mid_index) temp[s++] = median;
            while (t >= mid_index) temp[t--] = median;

            // Step 3: wiggle sort
            s = mid_index+1;
            t = nums.length;
            for (int i = 0; i < n; i++) {
                nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
            }
        }

        private int findKthLargest(int[] nums, int k) {
            int low = 0;
            int high = nums.length-1;

            return this.quickSort(nums, low, high, k);
        }

        private int quickSort(int[] nums, int low, int high, int k) {
            int l = low;
            int h = high;
            int pivot = nums[high];

            while (l < h) {

                if (nums[l++] > pivot) {
                    swap(nums, --l, --h);
                }
            }
            swap(nums, l, high);

            int m = l - low + 1;
            if (m == k) {
                return l;
            }
            else if (m > k) {
                return quickSort(nums, low, l-1, k);
            }
            else {
                return quickSort(nums, l+1, high, k-m);
            }

        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        WiggleSortII solution = new WiggleSortII();
        
        int[] nums = {1,1,2,1,2,2,1};
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
        
        
        int[] nums1 = {1, 5, 1, 1, 6, 4};
        WiggleSortII.Solution solution1 = new WiggleSortII.Solution();
        solution1.wiggleSort(nums1);
        System.out.println(Arrays.toString(nums1));
        
        int[] nums2 = {4, 5, 5, 6};
        solution.wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
