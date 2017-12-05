package LeetCode.Medium;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * 
 * Hint:
 * Expected runtime complexity is in O(log n) and the input is sorted.

 * @author WinnieZhao
 *
 */
public class HIndexII {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int len = citations.length;
        int left = 0;
        int right = len-1;

        while (left <= right) {
            int mid = left + (right-left)/2;

            if (citations[mid] == len-mid) {
                return len-mid;
            }
            else if (citations[mid] > len-mid) {
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }

        return len-left;
    }
}
