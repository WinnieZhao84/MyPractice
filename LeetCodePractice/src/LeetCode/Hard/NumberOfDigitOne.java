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
     * So for the numbers between [1, 99], except the [10, 19] range has 11 digits "1", all other ranges just have 1 for each
     * If we put aside for range [10, 19] first, for any two digits numbers, the tens digit position number plus 1 will be the
     * count of "1", after that we add the last 10.
     * For example, if n = 56; 1 digits count = (5+1) + 10 = 16
     *
     * How do we know if we need to add 10 additional count, we need to check the tens digit position to see if it's >= 2;
     * If it's >=2, we need to plus additional 10 (10 of "1"s). Use (x+8)/10 to check whether if a number >= 2.
     *
     * Similar logic for numbers more than 100, except [110, 119] has 21, all the other 3 digits numbers only have 11 of "1"
     */
    public int countDigitOne(int n) {

        int count = 0;

        for (long base = 1; base <= n; base *= 10) {
            long q = n/base, r = n%base;
            count += (q+8) / 10 * base + (q%10 == 1 ? r+1 : 0);
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfDigitOne solution = new NumberOfDigitOne();

        System.out.println(solution.countDigitOne(23));
    }
}
