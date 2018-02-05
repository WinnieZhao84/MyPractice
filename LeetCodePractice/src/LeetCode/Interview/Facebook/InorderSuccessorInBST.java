package LeetCode.Interview.Facebook;

import LeetCode.Helper.TreeNode;

/**
 *
 * 285
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
 *
 * Created by WinnieZhao on 2/4/2018.
 */
public class InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;

        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return succ;
    }

}
