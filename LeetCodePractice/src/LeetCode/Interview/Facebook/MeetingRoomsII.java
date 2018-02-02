package LeetCode.Interview.Facebook;

import LeetCode.Helper.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start);

        PriorityQueue<Integer> endTimesQueue = new PriorityQueue<>();
        endTimesQueue.offer(intervals[0].end);

        for (int i=1; i<intervals.length; i++) {
            Interval cur = intervals[i];
            if (cur.start >= endTimesQueue.peek()) {
                endTimesQueue.poll();
            }
            endTimesQueue.offer(cur.end);
        }

        return endTimesQueue.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII solution = new MeetingRoomsII();

        System.out.println(solution.minMeetingRooms(
                new Interval[] {new Interval(0, 30), new Interval(5, 10),new Interval(15, 20)}));
    }
}
