package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * 156
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root
 *
 * For example: Given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 * * Created by WinnieZhao on 2017/3/27.
 */
public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if(root == null) return root;
        if(root.left == null && root.right == null) return root;

        // Find the left mode node as the new root
        TreeNode newRoot = upsideDownBinaryTree(root.left);

        TreeNode newLeft = root.right;
        TreeNode oldLeft = root.left;

        root.left = null;
        root.right = null;
        oldLeft.left = newLeft;
        oldLeft.right = root;

        return newRoot;
    }

    public TreeNode upsideDownBinaryTree_iterator(TreeNode root) {
        TreeNode curr = root;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            TreeNode next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }

}
