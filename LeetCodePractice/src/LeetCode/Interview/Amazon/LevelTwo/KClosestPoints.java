package LeetCode.Interview.Amazon.LevelTwo;

import LeetCode.Helper.Point;

import java.util.PriorityQueue;

/**
 * Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest
 * to origin.
 *
 * Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.
 *
 * Example
 * Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
 * return [[1,1],[2,5],[4,4]]

 */
public class KClosestPoints {

    public Point[] kClosest(Point[] points, Point original, int k) {

        if(points == null) return points;
        final Point origin = original;

        PriorityQueue<Point> pq = new PriorityQueue<>(k, (a, b) -> {
            int distanceA = (int) (Math.pow((a.x - origin.x),2) + Math.pow((a.y - origin.y), 2));
            int distanceB = (int) (Math.pow((b.x - origin.x),2) + Math.pow((b.y - origin.y), 2));

            int sub = distanceB - distanceA;
            if (sub == 0) {
                sub = b.x - a.x;
                if (sub == 0) {
                    sub = b.y - a.y;
                }
            }
            return sub;
        });

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        Point[] result = new Point[k];

        int i = k - 1;
        while (!pq.isEmpty()) {
            result[i] = pq.poll();
            i--;
        }
        return result;
    }
}
