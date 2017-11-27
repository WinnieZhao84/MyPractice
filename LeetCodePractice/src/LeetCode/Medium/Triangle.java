package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle

 * @author WinnieZhao
 *
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int height = triangle.size();
        if (height == 1) {
            return triangle.get(0).get(0);
        }

        int[] sum = new int[height];

        // Bottom up solution
        for (int i=0; i<height; i++) {
            sum[i] = triangle.get(height-1).get(i);
        }

        for (int i=height-2; i>=0; i--) {
            for (int j=0; j<=i; j++) {
                sum[j] = Math.min(sum[j], sum[j+1]) + triangle.get(i).get(j);
            }
        }

        return sum[0];
    }
    
    public static void main(String[] args) {
        Integer[][] triangleArray = {{2},{3,4},{6,5,7},{4,1,8,3}};
        
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        for (Integer[] t : triangleArray) {
            List<Integer> list = Arrays.asList(t);
            triangle.add(list);
        }
        Triangle solution = new Triangle();
        System.out.println(solution.minimumTotal(triangle));
    }
}
