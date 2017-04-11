package LeetCode.Medium;

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

 * Created by WinnieZhao on 4/10/2017.
 */
public class MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {

        if(intervals == null || intervals.length == 0) return 0;
        if(intervals.length == 1) return 1;

        Comparator<Interval> comp = new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                if(a.start == b.start) return a.end - b.end;
                return a.start - b.start;
            }
        };

        Arrays.sort(intervals, comp);

        Comparator<Interval> comp2 = new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){

                return a.end - b.end;
            }
        };

        PriorityQueue<Interval> queue = new PriorityQueue<>(comp2);

        int count = 1;
        queue.offer(intervals[0]);

        for(int i=1; i< intervals.length; i++){
            Interval top = queue.peek();
            Interval next = intervals[i];
            if(next.start >= top.end){
                queue.poll();
            }
            else{
                count++;
            }

            queue.offer(next);
        }

        return count;
    }
}
