package LeetCode.Hard;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

 * Created by WinnieZhao on 2017/6/27.
 */
public class NumberOfDigitOne {

    /**
     * How many "1"      nums contains "1"                                       Range
     * 1                  1                                                      [1, 9]
     * 11                 10  11  12  13  14  15  16  17  18  19                 [10, 19]
     * 1                  21                                                     [20, 29]
     * 1                  31                                                     [30, 39]
     * 1                  41                                                     [40, 49]
     * 1                  51                                                     [50, 59]
     * 1                  61                                                     [60, 69]
     * 1                  71                                                     [70, 79]
     * 1                  81                                                     [80, 89]
     * 1                  91                                                     [90, 99]
     * 11                 100  101  102  103  104  105  106  107  108  109       [100, 109]
     * 21                 110  111  112  113  114  115  116  117  118  119       [110, 119]
     * 11                 120  121  122  123  124  125  126  127  128  129       [120, 129]
     *
     * If we don't look at those special rows (start with 10, 110 etc), we know that there's a one at ones' place in every 10 numbers,
     * there are 10 ones at tens' place in every 100 numbers, and 100 ones at hundreds' place in every 1000 numbers, so on and so forth.
     *
     * Ok, let's start with ones' place and count how many ones at this place, set k = 1, as mentioned above, there's a one at ones' place
     * in every 10 numbers, so how many 10 numbers do we have?
     * The answer is (n / k) / 10.
     *
     * Now let's count the ones in tens' place, set k = 10, as mentioned above, there are 10 ones at tens' place in every 100 numbers,
     * so how many 100 numbers do we have?
     * The answer is (n / k) / 10, and the number of ones at ten's place is (n / k) / 10 * k.
     *
     * Let r = n / k, now we have a formula to count the ones at k's place: r / 10 * k
     *
     * So far, everything looks good, but we need to fix those special rows, how?
     * We can use the mod. Take 10, 11, and 12 for example, if n is 10, we get (n / 1) / 10 * 1 = 1 ones at ones's place, perfect,
     * but for tens' place, we get (n / 10) / 10 * 10 = 0, that's not right, there should be a one at tens' place!
     * From 10 to 19, we always have a one at tens's place, let m = n % k, the number of ones at this special place is m + 1,
     * so let's fix the formula to be: r / 10 * k + (r % 10 == 1 ? m + 1 : 0)
     *
     * Wait, how about 20, 21 and 22?
     * Let's say 20, use the above formula we get 0 ones at tens' place, but it should be 10! How to fix it?
     * We know that once the digit is larger than 2, we should add 10 more ones to the tens' place,
     * a clever way to fix is to add 8 to r, so our final formula is: (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0)
     *
     */
    public int countDigitOne(int n) {

        int count = 0;

        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfDigitOne solution = new NumberOfDigitOne();

        System.out.println(solution.countDigitOne(56));
        System.out.println(solution.countDigitOne(119));
    }
}
