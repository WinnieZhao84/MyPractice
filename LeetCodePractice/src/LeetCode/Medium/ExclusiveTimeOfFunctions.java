package LeetCode.Medium;

import java.util.List;
import java.util.Stack;

/**
 * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.
 *
 * Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.
 *
 * A log is a string has this format : function_id:start_or_end:timestamp.
 * For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.
 *
 * Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered
 * as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.
 *
 * Example 1:
 * Input: n = 2
 * logs =
 * ["0:start:0", "1:start:2", "1:end:5", "0:end:6"]
 *
 * Output:[3, 4]
 *
 * Explanation:
 * Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
 * Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
 * Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
 * So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 *
 * Note:
 * Input logs will be sorted by timestamp, NOT log id.
 * Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
 * Two functions won't start or end at the same time.
 * Functions could be called recursively, and will always end. 1 <= n <= 100
 *
 * Created by WinnieZhao on 2017/7/16.
 */
public class ExclusiveTimeOfFunctions {

    /**
     * Before starting off with the solution, let's discuss a simple idea. Suppose we have three functions func_11, func_22
     * and func_3 such that func_1 calls func_2 and then func_2 calls func_3. In this case, func_3  starts at the end and
     * ends first, func_2 starts at 2nd position and ends at the 2nd last step. Similarly, func_1 starts first and ends at
     * the last position. Thus, we can conclude that the function which is entered at the end finishes first and the one
     * which is entered first ends at the last position. From the above discussion, we can conclude that we can make use
     * of a stack to solve the given problem.

     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {

        Stack<Integer> stack = new Stack<> ();
        int[] res = new int[n];

        String[] s = logs.get(0).split(":");
        stack.push(Integer.parseInt(s[0]));

        int i = 1;
        int prev = Integer.parseInt(s[2]);
        while(i<logs.size()) {
            s = logs.get(i).split(":");
            if (s[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += Integer.parseInt(s[2]) - prev;
                }

                stack.push(Integer.parseInt(s[0]));
                prev = Integer.parseInt(s[2]);
            }
            else {
                res[stack.peek()] += Integer.parseInt(s[2]) - prev + 1;
                stack.pop();
                prev = Integer.parseInt(s[2]) + 1;
            }
            i++;
        }

        return res;

    }

}
