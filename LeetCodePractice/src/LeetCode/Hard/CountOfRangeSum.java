package LeetCode.Hard;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note: A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

 * Created by WinnieZhao on 2017/6/5.
 */
public class CountOfRangeSum {

    /**
     * The merge sort based solution counts the answer while doing the merge. During the merge stage,
     * we have already sorted the left half [start, mid) and right half [mid, end). We then iterate
     * through the left half with index i. For each i, we need to find two indices k and j in the right half where
     *
     * j is the first index satisfy sums[j] - sums[i] > upper and
     * k is the first index satisfy sums[k] - sums[i] >= lower.
     *
     * Then the number of sums in [lower, upper] is j-k. We also use another index t to copy the elements satisfy
     * sums[t] < sums[i] to a cache in order to complete the merge sort.
     *
     * Despite the nested loops, the time complexity of the "merge & count" stage is still linear.
     * Because the indices k, j, t will only increase but not decrease, each of them will only traversal the right half once at most.
     * The total time complexity of this divide and conquer solution is then O(n log n).
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {

        int size = nums.length;
        long[] sums = new long[size+1];

        for (int i=0; i<size; i++) {
            sums[i+1] = sums[i] + nums[i];
        }

        return countWhileMergeSort(sums, 0, size + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = start + (end - start)/2;
        int count = this.countWhileMergeSort(sums, start, mid, lower, upper) + this.countWhileMergeSort(sums, mid, end, lower, upper);

        int j=mid;
        int k=mid;
        int t=mid;
        long[] cache = new long[end - start];

        for (int i=start, r = 0; i<mid; i++, r++) {
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }
            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }
            while (t < end && sums[t] < sums[i]) {
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}
