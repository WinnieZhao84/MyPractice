package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

public class MaximumDepthBinaryTree {

    public int maxDepth(TreeNode root) {
        return this.getDepth(0, root);
    }
    
    private int getDepth(int depth, TreeNode node) {
        
        if (node == null) {
            return depth;
        }
        depth++;
        
        if (node.left == null && node.right == null) {
            return depth;
        }
        int left = getDepth(depth, node.left);
        int right = getDepth(depth, node.right);
        
        return Math.max(left, right);
    }

    public int maxDepth_better(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getDepth(root);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}
