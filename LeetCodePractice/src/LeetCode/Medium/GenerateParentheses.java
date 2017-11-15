package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * [
 *    "((()))",
 *    "(()())",
 *    "(())()",
 *    "()(())",
 *    "()()()"
 * ]
 *
 * @author WinnieZhao
 *
 */
public class GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
   private void backtrack(List<String> list, String str, int open, int close, int max){
        
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    /**
     * Use String Builder instead of String concat to improve the performance
     * However, it requires to remove the last char when the backtrack returns
     *
     * @param n
     * @param sb
     * @param res
     * @param open
     * @param close
     */
    private void backtrack(int n, StringBuilder sb, List<String> res, int open, int close) {
        if (open == n && close == n) {
            res.add(sb.toString());
            return;
        }

        if (open < n) {
            this.backtrack(n, sb.append("("), res, open+1, close);
            sb.setLength(sb.length()-1);
        }

        if (close < open) {
            this.backtrack(n, sb.append(")"), res, open, close+1);
            sb.setLength(sb.length()-1);
        }
    }
   
   public static void main (String[] args) {
       GenerateParentheses solution = new GenerateParentheses();
       
       List<String> result = solution.generateParenthesis(3);
       
       System.out.println(result.stream().collect(Collectors.joining(",")));
   }
}
