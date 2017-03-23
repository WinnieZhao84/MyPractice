package LeetCode.Easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * For example,
 * MovingAverage m = new MovingAverage(3);
 *
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3

 * Created by WinnieZhao on 2017/3/22.
 */
public class MovingAverageFromDataStream {

    Queue<Integer> queue = null;
    int limit = 0;
    double total = 0;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        queue = new LinkedList<>();
        this.limit = size;
    }

    public double next(int val) {
        if (queue.size() < limit) {
            queue.add(val);
        }
        else {
            total = total - queue.poll();
        }
        total += val;
        return  total / queue.size();
    }
}
