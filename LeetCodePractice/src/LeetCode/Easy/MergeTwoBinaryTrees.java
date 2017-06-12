package LeetCode.Easy;

import LeetCode.Helper.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of new tree.
 *
 * Example 1:
 * Input:
 *                 Tree 1                     Tree 2
 *                 1                         2
 *                / \                       / \
 *               3   2                     1   3
 *              /                           \   \
 *             5                             4   7
 * Output:
 * Merged tree:
 *                3
 *               / \
 *              4   5
 *             / \   \
 *            5   4   7
 * Note: The merging process must start from the root nodes of both trees
 *
 * Created by WinnieZhao on 6/12/2017.
 */
public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        else if (t2 == null) {
            return t1;
        }

        TreeNode newTree = this.mergeHelper(t1, t2);

        return newTree;

    }

    private TreeNode mergeHelper(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        else if (t1==null) {
            return this.mergeHelper(new TreeNode(0), t2);
        }
        else if (t2==null) {
            return this.mergeHelper(t1, new TreeNode(0));
        }

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = this.mergeHelper(t1.left, t2.left);
        root.right = this.mergeHelper(t1.right, t2.right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.left.left = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.right = new TreeNode(2);
        t2.right.right = new TreeNode(3);

        MergeTwoBinaryTrees solution = new MergeTwoBinaryTrees();
        TreeNode result = solution.mergeTrees(t1, t2);
        System.out.print(result);
    }
}

