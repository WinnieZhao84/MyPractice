package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
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
    
    Map<String, List<Integer>> map = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        
        if (map.containsKey(input)) {
            return map.get(input);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1, input.length());
                
                char operator = input.charAt(i); 
                
                List<Integer> leftResult = this.diffWaysToCompute(part1);
                List<Integer> rightResult = this.diffWaysToCompute(part2);
                
                Integer res = null;
                for (Integer left : leftResult) {
                    for (Integer right : rightResult) {
                        if (operator == '+') {
                            res = left + right;
                        }
                        else if (operator == '-') {
                            res = left - right;
                        }
                        else if (operator == '*') {
                            res = left * right;
                        }
                        
                        result.add(res);
                    }
                }
            }
        }
        if (input.indexOf('+') < 0 && input.indexOf('-') < 0 && input.indexOf('*') < 0) {
            result.add(Integer.valueOf(input));
        }
        
        map.put(input, result);
        
        return result;
    }
    
    public static void main(String[] args) {
        DifferentWaysToAddParentheses solution = new DifferentWaysToAddParentheses();
        String input = "2-3-1";
        
        List<Integer> result = solution.diffWaysToCompute(input);
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(",")));
        
    }

}
