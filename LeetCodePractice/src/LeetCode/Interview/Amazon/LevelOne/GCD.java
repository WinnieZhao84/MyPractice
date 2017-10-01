package LeetCode.Interview.Amazon.LevelOne;

/**
 * 找一个数组中所有数的最大公约数
 * Created by WinnieZhao on 9/30/2017.
 */
public class GCD {

    public static int GCD(int[] input) {
        if (input.length == 1) return input[0];

        int res = input[0];
        for(int i = 1; i < input.length; i++) {
            res = helper(res, input[i]);
        }

        return res;
    }

    private static int helper(int a, int b) {
        if (b == 0) {
            return a;
        }
        return helper(b, a%b);
    }
}
