package LeetCode.Hard;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
 * write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are
 * the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 * It is guaranteed that 0 ? Li, Ri ? INT_MAX, 0 < Hi ? INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
 * Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline,
 * and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of
 * the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output
 * as such: [...[2 3], [4 5], [12 7], ...]
 *
 * Created by WinnieZhao on 7/10/2017.
 */
public class TheSkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> result = new ArrayList<>();

        /**
         * Sweepline is used in solving the problem. List<int[]> height is used to save each of the line segments
         * including both start and end point. The trick here is to set the start segment as negative height.
         * This has a few good uses:
         * 1) make sure the start segment comes before the end one after sorting.
         * 2) when pushing into the queue, it is very easy to distinguish either to add or remove a segment.
         * 3) when the two adjacent building share same start and end value, the next start segment always
         *    come in first due to the negative height, this makes sure that when we peek the queue,
         *    we always get the value we are supposed to get.
         *    When the first building is lower when we peek the queue, we get the height of the second building,
         *    and the first building will be removed in the next round of iteration.
         *    When the second building is lower, the first peek returns the first building and since it equals to prev,
         *    the height will not be added.
         */
        List<int[]> heights = new ArrayList<>();
        for(int[] b : buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }

        // Sort heights by x and then y in ascending order
        // {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}} =>
        // [2, -10], [3, -15], [5, -12], [7, 15], [9, 10], [12, 12], [15, -10], [19, -8], [20, 10], [24, 8]
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

        // Sort the priority queue in descending order
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        pq.offer(0);

        int highest = 0;
        for (int[] height : heights) {
            int h = height[1];
            int left = height[0];

            // Left point, add to queue
            if (h < 0) {
                pq.offer(-h);
            }
            // Right point, one sky line box ended, remove that height
            else {
                pq.remove(h);
            }

            int curHeight = pq.peek();
            if (highest != curHeight) {
                result.add(new int[] {left, curHeight});
                highest = curHeight;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TheSkylineProblem solution = new TheSkylineProblem();

        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};

        List<int[]> res = solution.getSkyline(buildings);
        System.out.println(res);
    }

    /**
     * pq.remove() is O(n) hence make it slower. Using TreeMap instead of PriorityQueue and the run time is 2.5X faster.
     *
     * @param buildings
     * @return
     */
    public List<int[]> getSkyline_accepted(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            heights.add(new int[]{b[0], - b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0,1);

        int prevHeight = 0;
        List<int[]> skyLine = new LinkedList<>();

        for (int[] h : heights) {
            if (h[1] < 0) {
                Integer cnt = heightMap.get(-h[1]);
                cnt = ( cnt == null ) ? 1 : cnt + 1;
                heightMap.put(-h[1], cnt);
            }
            else {
                Integer cnt = heightMap.get(h[1]);
                if (cnt == 1) {
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], cnt - 1);
                }
            }

            int currHeight = heightMap.firstKey();
            if (prevHeight != currHeight) {
                skyLine.add(new int[]{h[0], currHeight});
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }
}
