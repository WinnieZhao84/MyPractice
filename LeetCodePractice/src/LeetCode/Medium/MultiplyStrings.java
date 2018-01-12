package LeetCode.Medium;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * 
 * Note:
 * The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 * 
 * @author WinnieZhao
 *
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        else if (num1.equals("1") || num2.equals("1")) {
            return num1.equals("1") ? num2 : num1;
        }
        
        int len1 = num1.length();
        int len2 = num2.length();
        
        int[] res = new int[len1+len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int val1 = num1.charAt(i) - '0';
                int val2 = num2.charAt(j) - '0';

                int muliply = val1 * val2;

                int sum = res[i+j+1] + (muliply % 10);
                res[i+j+1] = sum % 10;
                res[i+j] += (sum / 10) + (muliply / 10);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : res) {
            if (!(sb.length() == 0 && n == 0)) {
                sb.append(n);
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        MultiplyStrings solution = new MultiplyStrings();
        System.out.println(solution.multiply("123", "456"));
    }
}
