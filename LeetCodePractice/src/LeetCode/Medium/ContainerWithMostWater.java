package LeetCode.Medium;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
 * which together with x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 *
 * [1  8  6  2  5  4  8  3  7]
 *     |              |
 *     |              |     |
 *     |  |           |     |
 *     |  |     |     |     |
 *     |  |     |  |  |     |
 *     |  |     |  |  |  |  |
 *     |  |  |  |  |  |  |  |
 *  |  |  |  |  |  |  |  |  |
 * @author WinnieZhao
 *
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int start = 0;
        int end = height.length-1;
        int max = 0;
        int water = 0;
        while (start < end) {
            water = (end - start) * Math.min(height[end], height[start]);
            max = Math.max(water, max);

            if (height[start] < height[end]) {
                start++;
            }
            else {
                end--;
            }
        }

        return max;
    }
}
