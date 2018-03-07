package LeetCode.Interview.Microsoft.LeetCode;

import LeetCode.Helper.TreeNode;

/**
 * 333
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 *
 * Note: A subtree must include all of its descendants. Here's an example:
 *      10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 *
 * The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 */
public class LargestBSTSubtree {

    private int size;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        size = 0;
        this.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return size;
    }

    private boolean isBST(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }

        boolean left = isBST(root.left, low, root.val);
        boolean right = isBST(root.right, root.val, high);

        if (left && right && root.val > low && root.val < high) {
            size++;
            return true;
        }

        return false;
    }
}
