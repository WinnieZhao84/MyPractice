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
public class ExcelSheetColumnTitle {
    
    public String convertToTitle(int n) {
        if ( n <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n = n-1;
            char ch = (char)('A' + n%26);
            sb.insert(0, ch);

            n = n / 26;
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        ExcelSheetColumnTitle solution = new ExcelSheetColumnTitle();
        
        System.out.println(solution.convertToTitle(53));
    }
}
