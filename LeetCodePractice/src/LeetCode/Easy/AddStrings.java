package LeetCode.Easy;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * 
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author WinnieZhao
 *
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        if ((num1 == null && num2 == null) || (num1.equals("") && num2.equals(""))) {
            return "";
        }
        
        int len1 = num1.length();
        int len2 = num2.length();
        
        int i=len1-1;
        int j=len2-1;
        
        int addon = 0;
        StringBuilder build = new StringBuilder();
        while (i>=0 || j>=0 || addon == 1) {
            int a = i>=0 ? (num1.charAt(i--) - '0') : 0;
            int b = j>=0 ? (num2.charAt(j--) - '0') : 0;
            int sum = addon + a + b;
            addon = 0;
            if (sum >= 10) {
                addon = 1;
                build.insert(0, String.valueOf(sum - 10));
            }
            else {
                build.insert(0, String.valueOf(sum));
            }
        }
        
        return build.toString();
    }
    
    public static void main(String[] args) {
        AddStrings solution = new AddStrings();
        System.out.println(solution.addStrings("23", "86"));
    }
}
