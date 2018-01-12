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

		if (numRows == 0) {
			return new ArrayList<>();
		}

		List<List<Integer>> res = new ArrayList<>();

		List<Integer> list = new ArrayList<>();
		list.add(1);
		res.add(list);

		for (int i=1; i<numRows; i++) {
			list = res.get(res.size()-1);

			List<Integer> level = new ArrayList<>();

			level.add(1);
			for (int j=1; j<list.size(); j++) {
				level.add(list.get(j-1) + list.get(j));
			}
			level.add(1);

			res.add(level);
		}

		return res;
    }
    
    public static void main(String[] args) {
    	PascalTriangle solution = new PascalTriangle();
    	
    	List<List<Integer>> result = solution.generate(6);
    	
    	for (List<Integer> num : result) {
    		System.out.println(Arrays.toString(num.toArray()));
    	}
    }
}
