package LeetCode.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 465
 *
 * A group of friends went on holiday and sometimes lent each other money.
 * For example, Alice paid for Bill's lunch for 10.Then later Chris gave Alice 10. Then later Chris gave Alice 5 for a taxi ride.
 * We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris
 * are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 *
 * Note:
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 *
 * Example 1:
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output: 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 *
 * Example 2:
 * Input: [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * Output: 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.

 * Created by WinnieZhao on 2017/7/17.
 */
public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {

        Map<Integer, Long> map = new HashMap();
        for(int[] t: transactions){
            long val1 = map.getOrDefault(t[0], 0L);
            long val2 = map.getOrDefault(t[1], 0L);
            map.put(t[0], val1 - t[2]);
            map.put(t[1], val2 + t[2]);
        }

        List<Long> list = new ArrayList();
        for(long val: map.values()){
            if (val != 0) {
                list.add(val);
            }
        }

        Long[] debts = new Long[list.size()];
        debts = list.toArray(debts);
        return helper(debts, 0 , 0);
    }

    /**
     * Starting from first debt debt[0], we look for all other debts debt[i] (i>0) which have opposite sign to debt[0].
     * Then each such debt[i] can make one transaction debt[i] += debt[0] to clear the person with debt debt[0].
     * From now on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons
     * one by one until everyone's debt is cleared meanwhile updating the minimum number of transactions during DFS.
     *
     * @param debts
     * @param pos
     * @param count
     * @return
     */
    int helper(Long[] debts, int pos, int count) {
        while(pos < debts.length && debts[pos] == 0) {
            pos++;
        }
        if (pos >= debts.length) {
            return count;
        }

        int res = Integer.MAX_VALUE;
        for(int i = pos + 1; i < debts.length; i++){
            if (debts[pos] * debts[i] < 0) {
                debts[i] += debts[pos];
                res = Math.min(res, helper(debts, pos + 1, count + 1));
                debts[i] = debts[i] - debts[pos];
            }
        }
        return res;
    }
}
