package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import LeetCode.Helper.Interval;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].

 * @author WinnieZhao
 *
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        intervals.sort(Comparator.comparingInt(a -> a.start));
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            // Overlapping
            if (interval.start <= end) {
                //start = Math.min(start, interval.start);
                end = Math.max(end, interval.end);
            }
            else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // Add the last interval
        result.add(new Interval(start, end));
        
        return result;
    }
}
