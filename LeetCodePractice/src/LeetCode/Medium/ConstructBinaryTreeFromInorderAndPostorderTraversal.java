package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree. 
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * @author WinnieZhao
 *
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        
        TreeNode root = this.buildTreeHelper(inorder, postorder, postorder.length-1, 0, inorder.length-1);
        
        return root;
    }
    
    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int postIndex, int inStart, int inEnd) {
        if (postIndex < 0 || inStart > inEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postIndex]);
        
        int inIndex = 0;
        for (int i= inStart; i<=inEnd; i++) {
            if (root.val == inorder[i]) {
                inIndex = i;
                break;
            }
        }
        
        root.left = this.buildTreeHelper(inorder, postorder, postIndex-1+inIndex-inEnd, inStart, inIndex-1);
        root.right = this.buildTreeHelper(inorder, postorder, postIndex-1, inIndex+1, inEnd);
        
        return root;
    }
    
    public static void main(String[] args) {
        int[] inorder = {2,1};
        int[] postorder = {2,1};
        
        ConstructBinaryTreeFromInorderAndPostorderTraversal solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        TreeNode root = solution.buildTree(inorder, postorder);
        System.out.println(root.val);

    }
}
