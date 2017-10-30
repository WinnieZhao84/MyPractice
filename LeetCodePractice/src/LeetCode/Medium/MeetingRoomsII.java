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

        Arrays.sort(intervals, (a,b) -> a.start == b.start ? a.end - b.end : a.start - b.start);

        Comparator<Interval> comp2 = Comparator.comparingInt(a -> a.end);

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

    public int minMeetingRooms_better(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

        // 用堆来管理房间的结束时间
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(intervals[0].end);
        
        for(int i = 1; i < intervals.length; i++){
            // 如果当前时间段的开始时间大于最早结束的时间，则可以更新这个最早的结束时间为当前时间段的结束时间，
            // 如果小于的话，就加入一个新的结束时间，表示新的房间
            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        // 有多少结束时间就有多少房间
        return endTimes.size();
    }
}
