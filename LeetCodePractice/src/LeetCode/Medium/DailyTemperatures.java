package LeetCode.Medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a list of daily temperatures, produce a list that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000].
 * Each temperature will be an integer in the range [30, 100]

 * Created by WinnieZhao on 12/31/2017.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {

        if (temperatures == null || temperatures.length <= 1) {
            return new int[0];
        }

        int len = temperatures.length;
        int[] res = new int[len];
        for (int i=0; i<len; i++) {
            int cur = temperatures[i];
            int count = 0;

            boolean warmer = false;
            for (int j=i+1; j<len; j++) {
                if (temperatures[j] > cur) {
                    warmer = true;
                }
                count++;
                if (warmer) {
                    break;
                }
            }
            if (warmer) {
                res[i] = count;
            }
        }

        return res;
    }

    /**
     * Use a stack to remember a list of indices representing a strictly increasing list of temperatures
     */
    public int[] dailyTemperatures_better(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();

        int[] res =  new int[temperatures.length];
        for (int i=temperatures.length-1; i>=0; i--) {

            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = 0;
            }
            else {
                res[i] = stack.peek()-i;
            }
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();

        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
