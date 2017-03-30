package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings representing two complex numbers.
 *
 * You need to return a string representing their multiplication. Note i^2 = -1 according to the definition.
 *
 * Example 1:
 *
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 *
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 *
 * Note:
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

 * Created by WinnieZhao on 2017/3/29.
 */
public class ComplexNumberMultiplication {

    public String complexNumberMultiply(String a, String b) {
        if (a == null || b == null) {
            return null;
        }

        int signA = 1;
        List<Integer> aList = new ArrayList<>();
        int num1 = 0;
        for (char c : a.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num1 = 10 * num1 + (c - '0');
            }
            else if (c == '+') {
                aList.add(num1* signA);
                num1 = 0;
                signA = 1;
            }
            else if (c == '-') {
                signA = -1;
            }
            else if (c == 'i') {
                aList.add(num1* signA);
            }
        }

        int signB = 1;
        List<Integer> bList = new ArrayList<>();
        int num2 = 0;
        for (char c : b.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num2 = 10 * num2 + (c - '0');
            }
            else if (c == '+') {
                bList.add(num2 * signB);
                num2 = 0;
                signB = 1;
            }
            else if (c == '-') {
                signB = -1;
            }
            else if (c == 'i') {
                bList.add(num2 * signB);
            }
        }

        Integer part1 = aList.get(0) * bList.get(0);
        Integer part2 = aList.get(1) * bList.get(0) + bList.get(1) * aList.get(0);
        Integer part3 = aList.get(1) * bList.get(1) * -1;

        String res1 = part1 + part3 == 0 ? "0" : String.valueOf(part1 + part3);
        String res2 = part2 + "i";
        String res = res1 + '+' + res2;

        return res;
    }

    public String complexNumberMultiply_clean(String a, String b) {
        String result = "";
        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(A[1].replace("i",""));

        int a2 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i",""));

        int a1a2 = a1 * a2;
        int b1b2 = b1 * b2;
        int a1b2a2b1 = (a1 * b2) + (b1 * a2);

        String afinal = (a1a2 + (-1 * b1b2)) + "";
        String bfinal = a1b2a2b1 + "i";
        result = afinal+"+"+bfinal;
        return result;
    }

    public static void main(String[] args) {
        ComplexNumberMultiplication solution = new ComplexNumberMultiplication();
        System.out.println(solution.complexNumberMultiply("1+1i", "1+1i"));

        System.out.println(solution.complexNumberMultiply("1+-1i", "1+-1i"));

        System.out.println(solution.complexNumberMultiply("2+-1i", "1+2i"));

        System.out.println(solution.complexNumberMultiply("78+-76i", "-86+72i"));

    }
}
