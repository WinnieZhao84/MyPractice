package LeetCode.Interview.Microsoft.LeetCode;

import LeetCode.Helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 545
 *
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 *
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Created by WinnieZhao on 2/26/2018.
 */
public class BoundaryOfBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        res.add(root.val);

        this.getLeftPath(root.left, res);
        this.getRightPath(root.right, res);

        return res;
    }

    private void getLeftPath(TreeNode left, List<Integer> res) {
        if (left != null) {

            res.add(left.val);

            if (left.left != null) {
                this.getLeftPath(left.left, res);
                this.getLeaves(left.right, res);
            }
            else {
                this.getLeftPath(left.right, res);
            }
        }
    }

    private void getRightPath(TreeNode right, List<Integer> res) {
        if (right != null) {

            res.add(right.val);

            if (right.right != null) {
                this.getLeaves(right.left, res);
                this.getRightPath(right.right, res);
            }
            else {
                this.getRightPath(right.left, res);
            }
        }
    }

    private void getLeaves(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                res.add(root.val);
            }
            else {
                this.getLeaves(root.left, res);
                this.getLeaves(root.right, res);
            }
        }
    }

}
