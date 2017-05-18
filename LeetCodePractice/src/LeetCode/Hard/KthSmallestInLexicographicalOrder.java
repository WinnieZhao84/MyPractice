package LeetCode.Hard;

/**
 *
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 *
 * Note: 1 ≤ k ≤ n ≤ 10^9.
 *
 * Example:
 * Input:
 * n: 13   k: 2
 *
 * Output: 10
 *
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 *
 * Created by WinnieZhao on 2017/5/16.
 */
public class KthSmallestInLexicographicalOrder {

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k-1;

        while(k>0) {
            int steps = this.calSteps(n, curr, curr+1);

            if (steps <= k) {
                curr += 1;
                k -= steps;
            } else {
                curr *= 10;
                k -= 1;
            }
        }

        return curr;
    }

    /**
     * how to calculate the steps between curr and curr + 1?
     * Here we come up a idea to calculate by level.
     * Let n1 = curr, n2 = curr + 1.
     * n2 is always the next right node beside n1's right most node (who shares the same ancestor "curr")
     * (refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is right next to 199).
     * so, if n2 <= n, what means n1's right most node exists, we can simply add the number of nodes from n1 to n2 to steps.
     * else if n2 > n, what means n (the biggest node) is on the path between n1 to n2, add (n + 1 - n1) to steps.
     * organize this flow to "steps += Math.min(n + 1, n2) - n1; n1 *= 10; n2 *= 10;"

     * @param n
     * @param n1
     * @param n2
     * @return
     */
    private int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while(n1<=n){
            steps += Math.min(n+1, n2) - n1;
            n1 = n1*10;
            n2 = n2*10;
        }

        return steps;
    }
}
