package LeetCode.Hard;

import LeetCode.Helper.Point;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Created by WinnieZhao on 2017/6/20.
 */
public class MaxPointsOnALine {

    public int maxPoints_better(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length < 2) {
            return points.length;
        }

        int len = points.length;

        int max = 0;
        for (int i=0; i<len; i++) {
            int curMax = 0;
            int same = 1;

            Map<String, Integer> sameSlope = new HashMap<>();
            for (int j=i+1; j<len; j++) {

                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    same++;
                    continue;
                }

                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;

                String slope = this.getSlope(dx, dy);
                sameSlope.put(slope, sameSlope.getOrDefault(slope, 0) + 1);

                curMax = Math.max(curMax, sameSlope.get(slope));
            }
            curMax = curMax + same;
            max = Math.max(max, curMax);
        }

        return max;
    }

    public int maxPoints(Point[] points) {
        if (points==null) return 0;
        if (points.length<=2) return points.length;

        int max = 0;
        for(int i = 0; i < points.length; i++){
            Map<BigDecimal, Integer> lines = new HashMap<>();

            int vertical = 0, same = 1, currMax = 0;
            for(int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    same++;
                    continue;
                }
                else if(points[i].x == points[j].x) {
                    vertical++;
                    continue;
                }

                BigDecimal dy = new BigDecimal(points[j].y - points[i].y);
                BigDecimal dx = new BigDecimal(points[j].x - points[i].x);
                BigDecimal slope = dy.divide(dx, MathContext.DECIMAL128);

                lines.put(slope, lines.getOrDefault(slope, 0) + 1);
                currMax = Math.max(currMax, lines.get(slope));
            }

            currMax = Math.max(vertical, currMax) + same;
            max = Math.max(currMax, max);
        }
        return max;
    }

    /**
     * Use String for storing the slope. "generateGcd" is used to get get the greatest common divider of the two numbers
     *
     * @param x
     * @param y
     * @return
     */
    public String getSlope(int x, int y) {
        int gcd = generateGcd(x, y);

        x /= gcd;
        y /= gcd;

        return x + "-" + y;
    }

    public int generateGcd(int x, int y) {
        if (y == 0) return x;
        return generateGcd(y, x % y);
    }

    public static void main(String[] args) {
        MaxPointsOnALine solution = new MaxPointsOnALine();
        System.out.println(solution.maxPoints_better(
                new Point[] {new Point(0,0), new Point(1,1), new Point(1,-1)}));
    }
}
