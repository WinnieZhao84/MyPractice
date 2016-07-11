package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the pre-order traversal of its nodes' values.
 * 
 * For example:
 * 
 * Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
   
   return [1,2,3]

 * @author WinnieZhao
 *
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal_Recursive(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        
        this.preorderTraversalHelper(root, result);
        
        return result;
    }
    
    /**
     * Recursive solution
     * 
     * @param root
     * @param result
     */
    private void preorderTraversalHelper(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
        }
        
        if (root.left != null) {
            this.preorderTraversalHelper(root.left, result);
        }
        
        if (root.right != null) {
            this.preorderTraversalHelper(root.right, result);
        }
    }
    
    /**
     * Iterative solution
     * 
     */
    public List<Integer> preorderTraversal_iterate(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        while (root != null) {
            if (root.right != null) {
                stack.push(root.right);
            }
            
            if (root.left != null) {
                stack.push(root.left);
            }
            result.add(root.val);
            
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
        };
        
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
        
        BinaryTreePreorderTraversal solution = new BinaryTreePreorderTraversal();
        
        System.out.println(solution.preorderTraversal_iterate(root));
    }
}
