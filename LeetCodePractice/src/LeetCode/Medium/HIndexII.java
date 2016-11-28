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
        int n = citations.length;
        if(n == 0) return 0;
        int min = 0, max = citations.length - 1;
        while(min <= max){
            int mid = (min + max) / 2;

            if(citations[mid] < n - mid){
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return n - min;
    }
}
