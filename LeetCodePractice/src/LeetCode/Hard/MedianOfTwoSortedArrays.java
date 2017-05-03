package LeetCode.Hard;

/**
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5
 * Created by WinnieZhao on 2017/5/2.
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int l1 = nums1.length;
        int l2 = nums2.length;

        int length = l1 + l2;

        int median = length / 2;
        boolean odd = length % 2 != 0;

        int[] res = new int[length];
        int start = 0;
        int i = 0;
        int j = 0;
        while (i<l1 && j<l2) {
            if (nums1[i] < nums2[j]) {
                res[start++] = nums1[i];
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                res[start++] = nums2[j];
                j++;

            }
            else {
                res[start++] = nums1[i];
                res[start++] = nums2[j];
                i++;
                j++;
            }

            if (start > median) {
                break;
            }
        }

        if (start <= median) {
            while (i<l1) {
                res[start++] = nums1[i++];
                if (start > median) {
                    break;
                }
            }
            while (j<l2) {
                res[start++] = nums2[j++];
                if (start > median) {
                    break;
                }
            }
        }

        double result = odd ? (double) res[median] : (double) (res[median-1] + res[median])/2;
        return result;
    }

    // O(log (m+n)) solution
    // Binary search. Call 2 times getkth and k is about half of (m + n). Every time call getkth can reduce the scale k to its half. So the time complexity is log(m + n).
    public double findMedianSortedArrays_better(int[] A, int[] B) {
        int len = A.length + B.length;
        if (len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }

}
