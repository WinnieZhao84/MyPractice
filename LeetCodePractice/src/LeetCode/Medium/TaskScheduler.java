package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing
 * different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example 1:
 * Input: tasks = ['A','A','A','B','B','B'], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].

 * Created by WinnieZhao on 2017/6/18.
 */
public class TaskScheduler {

    /**
     * Find the most frequent char(s) first, then there will be c[25]-1 intervals
     * Between the same task, it must have n spaces, so there will be (n + 1) length of chars for each section
     * e.g.
     * AAAABBBEEFFGG 3
     *
     * here X represents a space gap:
     * Frame: "AXXXAXXXAXXXA"
     * insert 'B': "ABXXABXXABXXA" <--- 'B' has higher frequency than the other characters, insert it first.
     * insert 'E': "ABEXABEXABXXA"
     * insert 'F': "ABEFABEXABFXA" <--- each time try to fill the k-1 gaps as full or evenly as possible.
     * insert 'G': "ABEFABEGABFGA"
     *
     * 25-i: count for the less frequence letters.
     */
    public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }

        Arrays.sort(c);

        int i = 25;
        while(i >= 0 && c[i] == c[25]) {
            i--;
        }

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

    public static void main(String[] args) {
        TaskScheduler solution = new TaskScheduler();

        char[] tasks = {'A','B','C','B','B','B'};
        System.out.println(solution.leastInterval(tasks, 2));
    }
}
