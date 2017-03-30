package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 362
 *
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume
 * that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 *
 * Follow up: What if the number of hits per second could be very large? Does your design scale?
 * use a queue to cache the hit, if the head of queue is of the 5min limit. pop it.

 * Created by WinnieZhao on 2017/3/30.
 */
public class DesignHitCounter {

    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     */

    Queue<Integer> window = new LinkedList<>();

    /** Initialize your data structure here. */
    public DesignHitCounter() {

    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        window.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!window.isEmpty() && timestamp - window.peek() >= 300){
            window.poll();
        }
        return window.size();
    }

    /**
     * For Follow up.
     * 使用两个数组times和hits,times数组里面放的是时间戳,hits数组里面放的是相应时间戳的点击次数,由于只需要统计5分钟(即300秒)以内的点击次数,
     * 但是时间戳是有可能大于300的,我们采取的措施是对时间戳取余,当往times数组里面存放时间戳时,如果之前存储的时间戳和现在要存放的不同,
     * 说明之前的时间戳已经过期,此时只需把times数组中的时间戳置为当前时间戳,hits数组相应位置置为1;如果相同,说明之前存储的时间戳和现在要存放的相同,
     * 只需hits数组里面相应位置增加1。当统计点击次数的时候,我们从前完后遍历一遍times数组,如果times数组中存储的时间戳和当前的时间戳的差值小于300,
     * 我们把hits数组里面相应位置的点击次数加起来即可
     */
    public class HitCounter {
        private int[] times;
        private int[] hits;
        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }
    }
}
