package LeetCode.Hard;

import LeetCode.Helper.Point;

import java.util.*;

/**
 * There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden.
 * Your job is to fence the entire garden using the minimum length of rope as it is expensive.
 * The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates
 * of trees which are exactly located on the fence perimeter.
 *
 * Example 1:
 * Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 *
 * Example 2:
 * Input: [[1,2],[2,2],[4,2]]
 * Output: [[1,2],[2,2],[4,2]]
 *
 * Even you only have trees in a line, you need to use rope to enclose them.
 *
 * Note:
 * All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
 * All input integers will range from 0 to 100.
 * The garden has at least one tree.
 * All coordinates are distinct.
 * Input points have NO order. No order required for output.

 * Created by WinnieZhao on 6/9/2017.
 */
public class ErectTheFence {

    private int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    public List <Point> outerTrees(Point[] points) {

        // Sort the points on the basis of their x-coordinate values.
        // If two points have the same x-coordinate values, the points
        // are sorted based on their y-coordinate values.
        Arrays.sort(points, (p, q) -> p.x - q.x == 0 ? p.y - q.y : p.x - q.x);


        /**
         * Traverse over the sorted points array after adding the initial two points in the hull temporarily
         * (which are pushed over the stack hull). For every new point considered, we check if the current
         * point lies in the counter-clockwise direction relative to the last two points.
         * If so, the current point is staightaway pushed onto hull. If not(indicated by a positive orientation),
         * we again get the inference that the last point on the hull needs to lie inside the boundary and not on the boundary.
         * Thus, we keep on popping the points from hull till the current point lies in a counterclockwise direction
         * relative to the top two points on the hull.
         *
         * FYI: http://www.geeksforgeeks.org/orientation-3-ordered-points/
         *
         */
        Stack <Point> hull = new Stack <> ();

        // Left most to right scan
        for (int i = 0; i < points.length; i++) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }

        hull.pop();

        // Right most to left scan
        for (int i = points.length - 1; i >= 0; i--) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }
        return new ArrayList<>(new HashSet<>(hull));
    }

    public static void main(String[] args) {
        Point[] points = {new Point(1,1), new Point(2,2), new Point(2,0), new Point(2,4), new Point(3,3), new Point(4,2)};

        ErectTheFence solution = new ErectTheFence();

        System.out.println(solution.outerTrees(points).toString());
    }
}
