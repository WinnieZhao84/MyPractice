package LeetCode.Interview.LinkedIn.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 254
 *
 * Numbers can be regarded as product of its factors.
 *
 * For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 *
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 *
 * Examples:
 * input: 1
 * output: []
 *
 * input: 37
 * output: []
 *
 * input: 12
 * output: [ [2, 6], [2, 2, 3], [3, 4] ]
 *
 * input: 32
 * output: [ [2, 16], [2, 2, 8], [2, 2, 2, 4], [2, 2, 2, 2, 2], [2, 4, 4], [4, 8] ]

 * Created by WinnieZhao on 2017/4/6.
 */
public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        //helper(result, new ArrayList<Integer>(), n, 2);

        helper(n, 2, result, new ArrayList<>());
        return result;

    }

    void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
        if (n == 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<>(item));
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n/i, i);
                item.remove(item.size()-1);
            }
        }
    }

    // better recursion
    void helper(int n, int start, List<List<Integer>> res, List<Integer> cur) {
        for (int i = start; i <= n/i; i++) {
            if (n % i > 0) {
                continue;
            }
            List<Integer> sub = new ArrayList<>(cur);
            sub.add(i);
            sub.add(n / i);

            res.add(sub);
            cur.add(i);

            helper(n / i, i, res, cur);
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        FactorCombinations solution = new FactorCombinations();

        System.out.println(solution.getFactors(32).toString());
    }
}
