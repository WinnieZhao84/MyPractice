package LeetCode.Hard;

/**
 * Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number
 * quickly from the multiplication table?
 *
 * Given the height m and the length n of a m * n Multiplication Table, and a positive integer k,
 * you need to return the k-th smallest number in this table.
 *
 * Example 1:
 * Input: m = 3, n = 3, k = 5
 *
 * Output:
 *
 * Explanation:
 * The Multiplication Table:
 * 1-->2-->3
 * 2-->4-->6
 * 3-->6-->9
 *
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 *
 * Example 2:
 * Input: m = 2, n = 3, k = 6
 *
 * Output:
 *
 * Explanation:
 * The Multiplication Table:
 * 1-->2-->3
 * 2-->4-->6
 *
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * Note:
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]


 */
public class KthSmallestNumberInMultiplicationTable {

    // Java solution, binary search
    public int findKthNumber(int m, int n, int k) {
        int low = 1;
        int high = m * n + 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int c = count(mid, m, n);
            if (c >= k) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        return high;
    }

    /**
     * The count function is to find how many numbers in the table are less than or equal to value v.
     * Since it is a multiplication table, and each number in the table is row*col,
     * we can find the amount of numbers row by row (or column by column).
     *
     * For the first row, r=1, the maximum possible c is v/1=v, or n.
     * Because c starts from 1, we can only have at most Math.min(v/1,n) values, which are less than or equal to v.
     *
     * For the second row, r=2, the maximum c is v/2, or n. Similarly, we can only have at most Math.min(v/2,n) values.
     * For the i-th row, r=i, the maximum c is v/i, or n.
     */
    private int count(int v, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(v / i, n);
            count += temp;
        }
        return count;
    }
}
