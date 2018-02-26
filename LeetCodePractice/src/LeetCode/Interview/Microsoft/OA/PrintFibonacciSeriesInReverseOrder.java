package LeetCode.Interview.Microsoft.OA;

/**
 * Given a number n then print n terms of fibonacci series in reverse order.
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
public class PrintFibonacciSeriesInReverseOrder {

    public void reverseFibonacci(int n) {
        int[] fab = new int[n];
        fab[0]=0;
        fab[1]=1;

        for (int i=2; i<n; i++) {
            fab[i] = fab[i-1] + fab[i-2];
        }

        for (int i = n - 1; i >= 0; i--) {
            System.out.print(fab[i] +" ");
        }
    }

    public static void main(String[] args) {
        PrintFibonacciSeriesInReverseOrder solution = new PrintFibonacciSeriesInReverseOrder();
        solution.reverseFibonacci(8);
    }
}
