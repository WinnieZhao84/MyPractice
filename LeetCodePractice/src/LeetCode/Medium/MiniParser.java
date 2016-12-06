package LeetCode.Medium;

import LeetCode.Helper.NestedInteger;

/**
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Note: You may assume that the string is well-formed:
 * 
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * 
 * Example 1:
 * 
 * Given s = "324",
 * You should return a NestedInteger object which contains a single integer 324.
 * 
 * Example 2:
 * 
 * Given s = "[123,[456,[789]]]",
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 *   i.  An integer containing value 456.
 *  ii. A nested list with one element:
 *    a. An integer containing value 789.
         
 * @author WinnieZhao
 *
 */
public class MiniParser {
    int i = 1;
    int start = i;
    
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }
        
        NestedInteger res = new NestedInteger();
        while (i < s.length()) {
            char c = s.charAt(i);
            
            if (c == '[') {
                start = ++i;
                NestedInteger ni = deserialize(s);
                res.add(ni);
            }
            else if (c == ']' || c == ',') {
                String num = s.substring(start, i);
                if (!num.equals("")) {
                    int n = Integer.valueOf(num);
                    NestedInteger ni = new NestedInteger(n);
                    res.add(ni);
                }
                start = ++i;
                if (c == ']') break;
            } 
            else {
                i++;
            }
        }
        return res;
    }
}
