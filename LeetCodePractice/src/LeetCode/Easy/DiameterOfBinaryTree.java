package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. 
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root.
 * 
 * Example: Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    

 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * 
 * @author WinnieZhao
 *
 */
public class DiameterOfBinaryTree {

    /**
     * Calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1.
     * While we do, a path "through" this node uses 1 + (depth of node.left) + (depth of node.right) nodes.
     * Search each node and remember the highest number of nodes used in some path.
     * The desired length is 1 minus this number
     *
     */
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        this.getDepth(root);

        return max-1;
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = this.getDepth(node.left);
        int right = this.getDepth(node.right);

        max = Math.max(left+right+1, max);

        return 1 + Math.max(left, right);
    }
}
