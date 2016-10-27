package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
    3
   / \
  9  20
    /  \
   15   7
   
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 * @author WinnieZhao
 *
 */
public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        } 
        else {
            sum += sumOfLeftLeaves(root.left);
        }
        
        sum += sumOfLeftLeaves(root.right);
        
        return sum;
    }
    
    public static void main(String[] args) {
        SumOfLeftLeaves solution = new SumOfLeftLeaves();
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println(solution.sumOfLeftLeaves(root));
    }
}
