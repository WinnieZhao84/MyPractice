package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *  
 * Example 1:
 *   2
 *  / \
 * 1   3
 *
 * Binary tree [2,1,3], return true.
 * Example 2:
 *   1
 *  / \
 * 2   3
 *
 * Binary tree [1,2,3], return false.
 * 
 * @author WinnieZhao
 *
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }
    
    private boolean isValidBSTHelper(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        return (low == null || node.val > low) && (high == null || node.val < high) && isValidBSTHelper(node.left, low, node.val) && isValidBSTHelper(node.right, node.val, high);
    }
    
    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(14);

        System.out.println(solution.isValidBST(root));
        
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(9);
        
        System.out.println(solution.isValidBST(root1));
        
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(8);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(9);
        
        System.out.println(solution.isValidBST(root2));
    }
}
