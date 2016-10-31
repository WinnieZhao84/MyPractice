package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 * 
 *  1
 * / \
 *2   3
 *
 *The root-to-leaf path 1->2 represents the number 12.
 *The root-to-leaf path 1->3 represents the number 13.
 *
 *Return the sum = 12 + 13 = 25.

 * @author WinnieZhao
 *
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        
        if (root.left == null && root.right == null) {
            return root.val;
        }
        
        return this.sumNumbersHelper(root, 0);
    }
    
    private int sumNumbersHelper(TreeNode root, int sum) {
        
        if (root == null) {
            return 0;
        }
        
        int res = root.val + sum * 10;
        if (root.left == null && root.right == null) {
            return res;
        }
        
        return this.sumNumbersHelper(root.left, res) + this.sumNumbersHelper(root.right, res);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(7);
        
        
        SumRootToLeafNumbers solution = new SumRootToLeafNumbers();
        System.out.println(solution.sumNumbers(root));
    }
}
