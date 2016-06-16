package LeetCode.Easy;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * @author ASUS-PC
 *
 */
public class PathSum {
	
	public boolean hasPathSum_recursively(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return this.hasPathSumHelper(root, sum - root.val);
    }
    
    private boolean hasPathSumHelper(TreeNode node, int sum) {
        TreeNode left = node.left;
        TreeNode right = node.right;
 
        if (left != null && right != null) {
           return this.hasPathSumHelper(left, sum - left.val) || this.hasPathSumHelper(right, sum - right.val);
        }
        else {
            if (left == null && right == null) {
                return sum == 0;
            }
            else {
                if (left == null) {
                    return this.hasPathSumHelper(right, sum - right.val);
                }
                else {
                    return this.hasPathSumHelper(left, sum - left.val);
                }
            }
        }
    }
    
    public boolean hasPathSum_iteratively(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> value = new LinkedList<Integer>();
        
        queue.offer(root);
        value.offer(root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i< size; i++) {
                TreeNode node = queue.poll();
                int total = value.poll();
                
                if (node.left == null && node.right == null && total == sum) {
                    return true;
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                    value.offer(total + node.left.val);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                    value.offer(total + node.right.val);
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        PathSum soluion = new PathSum();
        
        TreeNode root = new TreeNode(-2);
        TreeNode right = new TreeNode(-3);
        
        root.right = right;
        
        System.out.println(soluion.hasPathSum_recursively(root, -5));

    }
}
