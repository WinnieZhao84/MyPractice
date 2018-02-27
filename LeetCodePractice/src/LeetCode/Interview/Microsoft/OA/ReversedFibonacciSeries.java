package LeetCode.Interview.Microsoft.OA;

import java.util.Arrays;

/**
 * Given 2 integers, return the reversed Fibonacci series.
 *
 * Examples:
 * Input : n = 5
 * Output : 3 2 1 1 0
 *
 * Input : n = 8
 * Output : 13 8 5 3 2 1 1 0
 *
 * Created by WinnieZhao on 2/24/2018.
 */
public class ReversedFibonacciSeries {

    public int[] reverseFibonacci(int n1, int n2) {
        int[] fab = new int[10];
        fab[0]=n1;
        fab[1]=n2;

        for (int i=2; i<10; i++) {
            fab[i] = fab[i-2] - fab[i-1];

            if (fab[i] == 0) {
                break;
            }
        }

        return fab;
    }

    public static void main(String[] args) {
        ReversedFibonacciSeries solution = new ReversedFibonacciSeries();

        System.out.println(Arrays.toString(solution.reverseFibonacci(13, 8)));
    }
}
