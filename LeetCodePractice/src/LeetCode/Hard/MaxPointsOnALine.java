package LeetCode.Hard;

import LeetCode.Helper.Point;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Created by WinnieZhao on 2017/6/20.
 */
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if (points==null) return 0;
        if (points.length<=2) return points.length;

        int max = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<BigDecimal, Integer> lines = new HashMap<>();

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
}
