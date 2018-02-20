package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, 
 * and k is a real number (floating point number). Given some queries, return the answers. 
 * If the answer does not exist, return -1.0.
 * 
 * Example:
 * Given a / b = 2.0, b / c = 3.0. 
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
 * where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 * 
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 * 
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * 
 * Subscribe to see which companies asked this question

 * @author WinnieZhao
 *
 */
public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] result = new double[queries.length];
        
        Set<String> words = new HashSet<>();
        for (String[] strs : equations) {
            words.add(strs[0]);
            words.add(strs[1]);
        }
        
        for (int i=0; i<queries.length; i++) {
            String[] query = queries[i];
            
            if (!words.contains(query[0]) || !words.contains(query[1])) {
                result[i] = -1.0d;
            }
            else {
                Set<Integer> visited = new HashSet<>();
                result[i] = this.helper(query, equations, values, visited);
            }
        }
        return result;
    }
    
    private double helper(String[] query, String[][] equations, double[] values, Set<Integer> visited) {
        for (int i=0; i<equations.length; i++) {
            if (query[0].equals(equations[i][0]) && query[1].equals(equations[i][1])) {
                return values[i];
            }
            else if (query[1].equals(equations[i][0]) && query[0].equals(equations[i][1])) {
                return 1/values[i];
            }
        }
        
        for (int i=0; i<equations.length; i++) {
            if (!visited.contains(i) && query[0].equals(equations[i][0])) {
                visited.add(i);
                double temp = values[i] * this.helper(new String[] {equations[i][1], query[1]}, equations, values, visited);
                if (temp > 0) {
                    return temp;
                }
                visited.remove(i);
            }
            else if (!visited.contains(i) && query[0].equals(equations[i][1])) {
                visited.add(i);
                double temp = 1/values[i] * this.helper(new String[] {equations[i][0], query[1]}, equations, values, visited);
                if (temp > 0) {
                    return temp;
                }
                visited.remove(i);
            }
        }
        
        return -1.0d;
    }
}
