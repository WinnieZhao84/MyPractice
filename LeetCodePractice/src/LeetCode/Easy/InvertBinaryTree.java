package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

 * @author ASUS-PC
 *
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
    	if (root == null) {
    		return root;
    	}
    	this.invertTreeHelper(root);
    	return root;
    }
    
    private void invertTreeHelper(TreeNode node) {
    	
    	if (node == null) {
    		return;
    	}
    	
    	TreeNode temp = null;
    	
    	temp = node.left;
    	node.left = node.right;
    	node.right = temp;
    	
    	this.invertTreeHelper(node.left);
    	this.invertTreeHelper(node.right);
    }
}
