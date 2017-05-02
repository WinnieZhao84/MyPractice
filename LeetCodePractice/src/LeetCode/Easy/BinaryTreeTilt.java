package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 *
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of
 * all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 * Input:
 *    1
 *  /   \
 * 2     3
 *
 * Output: 1
 *
 * Explanation:
 * Tilt of node 2 : 0
 * Tilt of node 3 : 0
 * Tilt of node 1 : |2-3| = 1
 * Tilt of binary tree : 0 + 0 + 1 = 1
 *
 * Note:
 *
 * The sum of node values in any subtree won't exceed the range of 32-bit integer.
 * All the tilt values won't exceed the range of 32-bit integer.

 * Created by WinnieZhao on 2017/4/28.
 */
public class BinaryTreeTilt {

    int result = 0;
    public int findTilt(TreeNode root) {
        this.helper(root);
        return result;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum_left = helper(root.left);
        int sum_right = helper(root.right);
        result += Math.abs(sum_left - sum_right);

        return sum_left + sum_right + root.val;
    }

    public static void main(String[] args) {
        BinaryTreeTilt solution = new BinaryTreeTilt();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(solution.findTilt(root));

    }
}
