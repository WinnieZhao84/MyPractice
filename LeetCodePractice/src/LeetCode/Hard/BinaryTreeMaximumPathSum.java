package LeetCode.Hard;

import LeetCode.Helper.TreeNode;

/**
 *
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * For example: Given the below binary tree,
 *   1
 *  / \
 * 2   3
 *
 * Return 6.
 *
 * Created by WinnieZhao on 2017/5/11.
 */
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        this.helper(root);
        return max;

    }

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, this.helper(node.left));
        int right = Math.max(0, this.helper(node.right));

        max = Math.max(max, left + right + node.val);

        return Math.max(left, right) + node.val;
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(4);

        System.out.println(solution.maxPathSum(root));
    }
}
