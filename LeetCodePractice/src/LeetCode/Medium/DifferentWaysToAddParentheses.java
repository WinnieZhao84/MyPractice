package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 * 
 * Example 1
 * 
 * Input: "2-1-1".
 * 
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 
 * Output: [0, 2]
 * 
 * 
 * Example 2
 * 
 * Input: "2*3-4*5"
 * 
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * 
 * Output: [-34, -14, -10, -10, 10]
 * 
 * @author WinnieZhao
 *
 */
public class DifferentWaysToAddParentheses {

    Map<String, List<Integer>> cache = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        if (cache.containsKey(input)) {
            return cache.get(input);
        }

        List<Integer> res = new ArrayList<>();

        if (input.indexOf('+') < 0 && input.indexOf('-') < 0 && input.indexOf('*') < 0) {
            res.add(Integer.valueOf(input));
        }
        
        for (int i=0; i<input.length(); i++) {

            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {

                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1, input.length());

                char operator = input.charAt(i);

                List<Integer> left = diffWaysToCompute(part1);
                List<Integer> right = diffWaysToCompute(part2);

                if (left == null || right == null) {
                    continue;
                }

                for (Integer num1 : left) {
                    for (Integer num2 : right) {
                        if (operator == '+') {
                            res.add(num1 + num2);
                        }
                        else if (operator == '-') {
                            res.add(num1 - num2);
                        }
                        else if (operator == '*') {
                            res.add(num1 * num2);
                        }
                    }
                }
            }
        }

        cache.put(input, res);

        return res;
    }
    
    public static void main(String[] args) {
        DifferentWaysToAddParentheses solution = new DifferentWaysToAddParentheses();
        String input = "2-3-1";
        
        List<Integer> result = solution.diffWaysToCompute(input);
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(",")));
        
    }

}
