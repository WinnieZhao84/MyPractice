package LeetCode.Hard;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * For example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2

 * Created by WinnieZhao on 2017/5/11.
 */
public class FindMedianFromDataStream {

    PriorityQueue<Integer> highPriorityQueue  = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> lowPriorityQueue = new PriorityQueue<>();

    public FindMedianFromDataStream() {}

    public void addNum(int num) {
        highPriorityQueue.offer(num);
        lowPriorityQueue.offer(highPriorityQueue.poll());

        if (lowPriorityQueue.size() > highPriorityQueue.size()) {
            highPriorityQueue.offer(lowPriorityQueue.poll());
        }
    }

    public double findMedian() {
        if (highPriorityQueue.size() == lowPriorityQueue.size()) {
            return (highPriorityQueue.peek() + lowPriorityQueue.peek()) * 0.5;
        }
        else {
            return highPriorityQueue.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
