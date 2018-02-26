package LeetCode.Interview.Microsoft.LeetCode;

import LeetCode.Helper.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 *
 * Created by WinnieZhao on 2/25/2018.
 */
public class MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(i2 -> i2.start));

        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(intervals[0].end);

        for(int i = 1; i < intervals.length; i++){

            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        return endTimes.size();
    }
}
