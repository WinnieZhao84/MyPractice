package LeetCode.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 * @author ASUS-PC
 *
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
        if (numRows <= 0) {
        	return result;
        }
    	for (int i=1; i<= numRows; i++) {
    		List<Integer> level = new ArrayList<Integer>();
    		if (i<=2) {
    			for (int j=1; j<=i; j++) {
        			level.add(1);
    			}
    		}
    		else {
    			List<Integer> lastLevel = result.get(result.size()-1);
    			level.add(1);
    			for (int j=0; j<lastLevel.size()-1; j++) {
    				level.add(lastLevel.get(j)+lastLevel.get(j+1));
    			}
    			level.add(1);
    		}
			result.add(level);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	PascalTriangle solution = new PascalTriangle();
    	
    	List<List<Integer>> result = solution.generate(6);
    	
    	for (List<Integer> num : result) {
    		System.out.println(Arrays.toString(num.toArray()));
    	}
    }
}
