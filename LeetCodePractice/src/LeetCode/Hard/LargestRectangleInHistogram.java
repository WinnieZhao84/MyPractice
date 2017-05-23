package LeetCode.Hard;

import java.util.Stack;

/**
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.

 * Created by WinnieZhao on 2017/5/18.
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();// stack for index to caculate width.
        int max = 0;
        int i = 0;
        while ( i <= heights.length ) {
            int val = i < heights.length ? heights[i] : 0;
            if(stack.empty() || val >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            }
            else{
                int h = stack.pop();
                int w = stack.empty() ? i : i - stack.peek() - 1;

                max = Math.max(max, heights[h] * w);
            }
        }

        return max;

    }
}
