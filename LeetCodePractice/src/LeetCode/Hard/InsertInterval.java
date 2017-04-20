package LeetCode.Hard;

import LeetCode.Helper.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

 * Created by WinnieZhao on 4/20/2017.
 */
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        if (intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        List<Interval> result = new ArrayList<>();

        int i=0;
        for (; i<intervals.size(); i++) {
            Interval current = intervals.get(i);

            if (current.end < newInterval.start) {
                result.add(current);
            }
            else {
                break;
            }
        }

        // merge
        for (; i<intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (newInterval.end < current.start) {
                break;
            }
            else {
                newInterval.start = Math.min(newInterval.start, current.start);
                newInterval.end = Math.max(newInterval.end, current.end);
            }
        }

        result.add(newInterval);

        for (; i<intervals.size(); i++) {
            result.add(intervals.get(i));
        }

        return result;
    }

}
