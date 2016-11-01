package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * @author WinnieZhao
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }

        TreeNode root = this.buildTreeHelper(0, 0, inorder.length - 1, preorder, inorder);
        
        return root;
    }
    
    private TreeNode buildTreeHelper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }
        root.left = buildTreeHelper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = buildTreeHelper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,8,9,3,6,7};
        int[] inorder = {4,2,8,5,9,1,6,3,7};
        
        ConstructBinaryTreeFromPreorderAndInorderTraversal solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println(root.val);

    }

}
