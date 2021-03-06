package LeetCode.Interview.LinkedIn.LeetCode;

import LeetCode.Helper.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * 364
 *
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Different from the previous question where weight is increasing from root to leaf, now the weight
 * is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers
 * have the largest weight.
 *
 * Example 1: Given the list [[1,1],2,[1,1]], return 8.
 * (four 1's at depth 1, one 2 at depth 2)
 *
 * Example 2: Given the list [1,[4,[6]]], return 17.
 * (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

 * Created by WinnieZhao on 2017/4/4.
 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> res = new ArrayList<>();

        this.depthSumInverseHelper(nestedList, res, 1);

        int size = res.size();
        int sum = 0;
        for (Integer i : res) {
            sum += i * size;
            size--;
        }

        return sum;
    }

    private void depthSumInverseHelper(List<NestedInteger> nestedList, List<Integer> res, int depth) {
        while (res.size() <= depth) {
            res.add(0);
        }

        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                Integer value = res.get(depth) + nestedInteger.getInteger();
                res.set(depth, value);
            }
            else {
                this.depthSumInverseHelper(nestedInteger.getList(), res, depth+1);
            }
        }
    }
}
