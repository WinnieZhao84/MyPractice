package LeetCode.Hard;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved. Return an array of the k digits.
 * You should try to optimize your time and space complexity.
 *
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 *
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 *
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9]

 * Created by WinnieZhao on 2017/6/21.
 */
public class CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        // E.g., nums1 length 4, nums2 length 2; k=5, so nums1 at least needs to pick 3 (k-nums2.length)
        for (int i = Math.max(k - nums2.length, 0); i <= Math.min(nums1.length, k); i++) {
            int[] res1 = get_max_sub_array(nums1, i);
            int[] res2 = get_max_sub_array(nums2, k - i);

            int[] res = new int[k];
            int pos1 = 0, pos2 = 0, tpos = 0;

            // Compare the res1 and res1, find the greater between the two
            while (pos1 < res1.length || pos2 < res2.length) {
                res[tpos++] = greater(res1, pos1, res2, pos2) ? res1[pos1++] : res2[pos2++];
            }

            if (!greater(ans, 0, res, 0))
                ans = res;
        }

        return ans;
    }

    // Compare the specific index of each of two arrays, choose the greater one
    // However, if two of them are the same value, keep doing ++ and choose the
    // equal value from the longer arrays, because the longer array will probably
    // have the greater value from following numbers. so we want that number
    // appears firstly
    private boolean greater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
        }
        return start1 != nums1.length;
    }

    private int[] get_max_sub_array(int[] nums, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            // "len + nums.length - i > k" to ensure at least we need to have k number digits in the result array
            // "nums.length-i" means how many numbers are left
            while (len > 0 && len + nums.length - i > k && res[len - 1] < nums[i]) {
                len--;
            }
            if (len < k)
                res[len++] = nums[i];
        }
        return res;
    }
}
