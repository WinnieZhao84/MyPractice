package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
 * the two subtrees of every node never differ by more than 1.
 * 
 * @author WinnieZhao
 *
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        
        if (root == null) {
            return true;
        }
        
        return getDepth(root) != -1;
    }
    
    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftDepth = this.getDepth(node.left);
        int rightDepth = this.getDepth(node.right);
        
        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
