package LeetCode.Medium;

import java.util.List;

/**
 * 469
 *
 * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).
 *
 * Note:
 * There are at least 3 and at most 10,000 points.
 * Coordinates are in the range -10,000 to 10,000.
 * You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition).
 * In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
 *
 * Example 1:
 * [[0,0],[0,1],[1,1],[1,0]]
 * Answer: True
 *
 * Example 2:
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 * Answer: False
 *
 * Created by WinnieZhao on 4/11/2017.
 */
public class ConvexPolygon {

    /**
     * get the cross product of the sequential input edge a, b as tmp, then:
     *
     * if tmp == 0, a -> b 180° on the same line;
     * else if tmp > 0, a -> b clockwise;
     * else tmp < 0, a -> anticlockwise;
     *
     * tmp = (p1[0]-p0[0])(p2[1]-p0[1])-(p2[0]-p0[0])(p1[1]-p0[1])
     */

    public boolean isConvex(List<List<Integer>> points) {
        // For each set of three adjacent points A, B, C, find the cross product AB · BC. If the sign of
        // all the cross products is the same, the angles are all positive or negative (depending on the
        // order in which we visit them) so the polygon is convex.
        boolean gotNegative = false;
        boolean gotPositive = false;
        int numPoints = points.size();
        int B, C;

        for (int A = 0; A < numPoints; A++) {
            // Trick to calc the last 3 points: n - 1, 0 and 1.
            B = (A + 1) % numPoints;
            C = (B + 1) % numPoints;

            int crossProduct =
                    crossProductLength(
                            points.get(A).get(0), points.get(A).get(1),
                            points.get(B).get(0), points.get(B).get(1),
                            points.get(C).get(0), points.get(C).get(1));
            if (crossProduct < 0) {
                gotNegative = true;
            }
            else if (crossProduct > 0) {
                gotPositive = true;
            }
            if (gotNegative && gotPositive) return false;
        }

        // If we got this far, the polygon is convex.
        return true;
    }

    // Return the cross product AB x BC.
    // The cross product is a vector perpendicular to AB and BC having length |AB| * |BC| * Sin(theta) and
    // with direction given by the right-hand rule. For two vectors in the X-Y plane, the result is a
    // vector with X and Y components 0 so the Z component gives the vector's length and direction.
    private int crossProductLength(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
        // Get the vectors' coordinates.
        int BAx = Ax - Bx;
        int BAy = Ay - By;
        int BCx = Cx - Bx;
        int BCy = Cy - By;

        // Calculate the Z coordinate of the cross product.
        return (BAx * BCy - BAy * BCx);
    }
}