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

        int len1 = num1.length()-1;
        int len2 = num2.length()-1;

        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (len1 >= 0 || len2 >=0 || carry > 0) {
            int a = len1>=0 ? (num1.charAt(len1--) - '0') : 0;
            int b = len2>=0 ? (num2.charAt(len2--) - '0') : 0;

            int sum = a + b + carry;
            carry = sum/10;
            sum = sum%10;

            sb.insert(0, String.valueOf(sum));
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        AddStrings solution = new AddStrings();
        System.out.println(solution.addStrings("23", "86"));
    }
}
