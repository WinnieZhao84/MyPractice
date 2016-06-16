package LeetCode.Easy;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    
 * @author WinnieZhao
 *
 */
public class ExceelSheetColumnNumber {
    
    public int titleToNumber(String s) {
        
        int result = 0;
        for (int i=0; i<=s.length()-1; i++) {
            char ch = s.charAt(i);
            int carrier = ch - 'A' + 1;
            
            result = result * 26 + carrier;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        ExceelSheetColumnNumber solution = new ExceelSheetColumnNumber();
        
        System.out.println(solution.titleToNumber("ABA"));
    }
}
