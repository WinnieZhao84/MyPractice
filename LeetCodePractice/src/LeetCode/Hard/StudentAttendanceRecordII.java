package LeetCode.Hard;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
 * The answer may be very large, return it after mod 10^9 + 7.
 *
 * A student attendance record is a string that only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 *
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * Example 1:
 * Input: n = 2
 * Output: 8
 *
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.

 * Created by WinnieZhao on 4/19/2017.
 */
public class StudentAttendanceRecordII {

    /**
     * dp[i]: the number of all possible attendance (without 'A') records with length i :
     * end with "P": dp[i-1]
     * end with "PL": dp[i-2]
     * end with "PLL": dp[i-3]
     * end with "LLL": is not allowed
     *
     * so dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
     *
     * the number of all possible attendance (with 'A') records with length n: ∑(dp[i] * dp[n-1-i]) (i = 0,1,...,n-1)
     *
     */
    static final int M = 1000000007;

    public int checkRecord(int n) {
        if (n == 0) return 0;

        long[] PorL = new long[n + 1]; // ending in P or L, no A
        long[] P = new long[n + 1]; // ending in P, no A
        PorL[0] = P[0] = 1; PorL[1] = 2; P[1] = 1;

        for (int i = 2; i <= n; i++) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % M;
        }

        long res = PorL[n];
        for (int i = 0; i < n; i++) { // inserting A
            long s = (PorL[i] * PorL[n - i - 1]) % M;
            res = (res + s) % M;
        }

        return (int) res;
    }
}
