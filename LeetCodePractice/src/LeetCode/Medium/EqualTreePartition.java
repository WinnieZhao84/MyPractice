package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to
 * two trees which have the equal sum of values after removing exactly one edge on the original tree.
 * Example 1:
 * Input:  5
 *        / \
 *       10 10
 *         /  \
 *        2   3
 *
 * Output: True
 * Explanation:
 *     5
 *    /
 *   10
 * Sum: 15
 *   10
 *  /  \
 * 2    3
 *
 * Sum: 15
 *
 * Example 2:
 * Input:   1
 *         / \
 *        2  10
 *          /  \
 *         2   20
 *
 * Output: False
 * Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 *
 * Note:
 * The range of tree node value is in the range of [-100000, 100000].
 * 1 <= n <= 10000

 * Created by WinnieZhao on 8/21/2017.
 */
public class EqualTreePartition {

    Set<Integer> sumSet = new HashSet<>();
    public boolean checkEqualTree(TreeNode root) {
        if (root.left == null && root.right == null) return false;

        int sum = this.findSum(root);
        if (sum % 2 != 0) {
            return false;
        }

        int key = sum / 2;
        return sumSet.contains(key);
    }

    private int findSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = this.findSum(root.left) + this.findSum(root.right) + root.val;
        sumSet.add(sum);

        return sum;
    }
}
