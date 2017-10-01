package LeetCode.Interview.Amazon.LevelTwo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一个处理器要处理一堆request，一次只能处理一条，如果它有几个积压着的requests，它会先执行持续时间短的那个；
 * 对于持续时间相等的requests，先执行最早到达处理器的request。问平均每个request要等多久才能被处理。
 *
 * input：requestTimes[]，每个request到达处理器的时间;
 * durations[] 每个request要处理的持续时间。
 *
 * 两个数组是一一对应的，并已按requestTimes[] 从小到大排序过
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class ShortedJobFirst {

    class Process {
        int arrTime;
        int exeTime;

        Process(int arr, int exe) {
            arrTime = arr;
            exeTime = exe;
        }
    }

    public float Solution(int[] requestTimes, int[] durations) {
        if (requestTimes == null || durations == null || requestTimes.length != durations.length)
            return 0;

        int index = 0;
        int length = requestTimes.length;
        int waitTime = 0;
        int curTime = 0;

        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.exeTime == p2.exeTime)
                return p1.arrTime - p2.arrTime;
            return p1.exeTime - p2.exeTime;
        });

        while (!pq.isEmpty() || index < length) {
            if (!pq.isEmpty()) {
                Process cur = pq.poll();
                waitTime += curTime - cur.arrTime;
                curTime += cur.exeTime;

                while (index < length && curTime >= requestTimes[index]) {
                    pq.offer(new Process(requestTimes[index], durations[index]));
                    index++;
                }
            }
            else {
                pq.offer(new Process(requestTimes[index], durations[index]));
                curTime = requestTimes[index++];
            }
        }
        return (float) waitTime / length;
    }
}
