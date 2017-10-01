package LeetCode.Interview.Amazon.LevelTwo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个处理器要处理一堆request，一次只能处理一条，每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。
 * 若前一个任务没执行完则放到队尾，等待下一次执行。
 * 假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了，另外Robin Round最后返回值是float
 *
 * Explain: https://www.youtube.com/watch?v=aWlQYllBZDs

 * Created by WinnieZhao on 9/30/2017.
 */
public class RourdRobin {
    static class Task {
        int arrive;
        int remain;
        public Task(int arrive, int remain) {
            this.arrive = arrive;
            this.remain = remain;
        }
    }

    public static double roundRobin(int[] arriveTime, int[] runTime, int slot) {
        Queue<Task> queue = new LinkedList<>();
        int i = 0, curTime = 0, wait = 0;
        while(i < arriveTime.length || !queue.isEmpty()) {
            if(!queue.isEmpty()) {
                Task peek = queue.poll();
                wait += (curTime - peek.arrive);

                if(peek.remain > slot) {
                    curTime += slot;
                    peek.remain -= slot;
                    peek.arrive = curTime;
                }
                else {
                    curTime += peek.remain;
                    peek.remain = 0;
                    //peek.arrive = curTime;
                }
                while(i < arriveTime.length && arriveTime[i] <= curTime) {
                    queue.offer(new Task(arriveTime[i], runTime[i]));
                    i++;
                }

                if(peek.remain != 0) {
                    queue.offer(peek);
                }
            }
            else {
                queue.offer(new Task(arriveTime[i], runTime[i]));
                curTime = arriveTime[i];
                i++;
            }
        }
        return (wait + 0.0) / arriveTime.length;
    }

    public static void main(String[] args) {

        int[] arriveTime = {0, 1, 4};
        int[] runTime = {5, 2, 3};

        System.out.println(roundRobin(arriveTime, runTime, 3));
    }
}
