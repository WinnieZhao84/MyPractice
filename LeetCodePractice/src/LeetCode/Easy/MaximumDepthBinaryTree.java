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
}
