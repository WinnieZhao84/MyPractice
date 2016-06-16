package LeetCode.Easy;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 * @author WinnieZhao
 *
 */
public class ExceelSheetColumnTitle {
    
    public String convertToTitle(int n) {
       
        String result = "";
        while (n>0) {
            int reminder = (n-1) % 26;
            result = (char) (reminder + 'A') + result;
            n = (n - reminder) / 26;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        ExceelSheetColumnTitle solution = new ExceelSheetColumnTitle();
        
        System.out.println(solution.convertToTitle(53));
    }
}
