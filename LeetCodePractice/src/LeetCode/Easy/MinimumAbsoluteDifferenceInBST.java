package LeetCode.Easy;

import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * Input:
 *  1
 *   \
 *    3
 *   /
 *  2
 *  
 *  Output: 1
 *  
 *  Explanation:
 *  
 *  The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *  Note: There are at least two nodes in this BST.

 * @author WinnieZhao
 *
 */
public class MinimumAbsoluteDifferenceInBST {

    /**
     * Since this is a BST, the inorder traversal of its nodes results in a sorted list of values.
     * Thus, the minimum absolute difference must occur in any adjacently traversed nodes.
     * Use the global variable "prev" to keep track of each node's inorder predecessor.
     *
     */
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        if (pre != null) {
            min = Math.min(min, Math.abs(root.val - pre.val));
        }

        pre = root;

        inorder(root.right);
    }
    


}
