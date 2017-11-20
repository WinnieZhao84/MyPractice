package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example:
 * 
 * Given binary tree [1,null,2,3],
   
   1
    \
     2
    /
   3
   
   return [1,3,2].

 * @author WinnieZhao
 *
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        
        this.inorderTraversalHelper(root, result);
        
        return result;
    }
    
    /**
     * Recursive solution
     * 
     * @param root
     * @param result
     */
    private void inorderTraversalHelper(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            this.inorderTraversalHelper(root.left, result);
        }
        
        if (root != null) {
            result.add(root.val);
        }
        
        if (root.right != null) {
            this.inorderTraversalHelper(root.right, result);
        }
    }
    
    /**
     * Iterative solution
     * 
     */
    public List<Integer> inorderTraversal_iterate(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode node = root;
        
        while(node!=null || !stack.empty()){
            while(node!=null){
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        
        root.left = left1;
        root.right = right1;
        
        TreeNode leftLeft = new TreeNode(4);
        
        left1.left = leftLeft;
        
        BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();
        
        System.out.println(solution.inorderTraversal(root));
    }
}
