package LeetCode.Interview.Facebook;

import LeetCode.Helper.Interval;

import java.util.Arrays;

/**
 * 252
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * For example，
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 * Created by WinnieZhao on 2/4/2018.
 */
public class MeetingRooms {

    public boolean canAttendMeetings(Interval[] intervals) {

        if (intervals == null || intervals.length == 0) return true;

        int length = intervals.length;
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        int end = intervals[0].end;
        for (int i=1; i<intervals.length; i++) {
            if (intervals[i].start < end) {
                return false;
            }
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }
}
