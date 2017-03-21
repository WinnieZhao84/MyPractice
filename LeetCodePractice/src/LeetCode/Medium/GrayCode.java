package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * Note: For a given n, a gray code sequence is not uniquely defined. 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code sequence.

 * @author WinnieZhao
 *
 */
public class GrayCode {
    
    public List<Integer> grayCode(int n) {
        if (n < 0) return new ArrayList<Integer>();
        
        if (n == 0) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(0);
            return list;
        }
        
        List<Integer> tmp = grayCode(n - 1);
        List<Integer> result = new ArrayList<Integer>(tmp);
        
        int addNumber = 1 << (n - 1);
        
        for (int i = tmp.size() - 1; i >= 0; i--) {
            result.add(addNumber + tmp.get(i));
        }

        return result;
    }
    
    public static void main (String[] args) {
        GrayCode solution = new GrayCode();
        
        List<Integer> result = solution.grayCode(3);
        
        result.forEach(r -> System.out.println(r));
        
        //System.out.println(1<<0);
    }
}
