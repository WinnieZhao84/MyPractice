package LeetCode.Medium;

import java.util.Arrays;

import LeetCode.Helper.Interval;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * 
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * Output: 1 Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * 
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 * Output: 2 Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * 
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * 
 * @author WinnieZhao
 *
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        /**
         * 1) sort by end, smaller end in front
         * 2) if end is same, sort by start, bigger start in front
         */
        Arrays.sort(intervals, (x, y) -> x.end != y.end ? x.end - y.end : y.start - x.start);
        
        int count = 0;
        int end = Integer.MIN_VALUE;
        
        for (Interval interval : intervals) {
            if (interval.start >= end) {
                end = interval.end;
            }
            else {
                count++;
            }
        }
        
        return count;
    }
}
