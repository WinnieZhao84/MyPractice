package LeetCode.Hard;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        int m = A.length, n = B.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    }

    // find kth number of two sorted array
    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart > A.length - 1) return B[bStart + k - 1];
        if (bStart > B.length - 1) return A[aStart + k - 1];
        if (k == 1) return Math.min(A[aStart], B[bStart]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1];
        if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];

        if (aMid < bMid)
            return getkth(A, aStart + k/2, B, bStart,       k - k/2);// Check: aRight + bLeft
        else
            return getkth(A, aStart,       B, bStart + k/2, k - k/2);// Check: bRight + aLeft
    }

    public double findMedianSortedArrays_test(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> low = new PriorityQueue(Comparator.reverseOrder());
        PriorityQueue<Integer> high = new PriorityQueue();

        this.process(low, high, nums1);
        this.process(low, high, nums2);

        if (low.size() > high.size()) {
            return low.peek();
        }
        else {
            return ((double)low.peek() + (double)high.peek()) / 2;
        }
    }

    private void process(PriorityQueue<Integer> low, PriorityQueue<Integer> high, int[] nums) {

        for (int num : nums) {
            low.offer(num);
            high.offer(low.poll());

            if (low.size() < high.size()) {
                low.offer(high.poll());
            }
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
        int[] nums1 = {4,5,6,8,9};
        int[] nums2 = {};

        System.out.println(solution.findMedianSortedArrays_test(nums1, nums2));
    }

}
