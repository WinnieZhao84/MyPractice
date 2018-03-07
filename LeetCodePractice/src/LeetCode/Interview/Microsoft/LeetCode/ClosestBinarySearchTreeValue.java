package LeetCode.Interview.Microsoft.LeetCode;

import LeetCode.Helper.TreeNode;

/**
 * 270
 *
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is
 * closest to the target. Basic observation: if root.val is greater than target value, the right subtree can't be
 * closer than root/left subtree.
 *
 */
public class ClosestBinarySearchTreeValue {

    public int closestValue_DFS(TreeNode root, double target) {
        TreeNode child = root.val < target ? root.right : root.left;
        if (child == null) {
            return root.val;
        }

        int childClose = closestValue_DFS(child, target);

        return Math.abs(target-childClose) > Math.abs(target-root.val) ? root.val : childClose;
    }

    public int closestValue_BFS(TreeNode root, double target) {

        int closest = root.val;
        double min = Double.MAX_VALUE;

        while(root!=null) {
            if( Math.abs(root.val - target) < min  ) {
                min = Math.abs(root.val - target);
                closest = root.val;
            }

            if(target < root.val) {
                root = root.left;
            }
            else if(target > root.val) {
                root = root.right;
            }
            else {
                return root.val;
            }
        }

        return closest;
    }
}
