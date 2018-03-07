package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * 
 * Example 1:
 * Input:
 * 
 *   2
 *  / \
 * 1   3
 * 
 * Output: 1
 * 
 * Example 2: 
 * Input:
 *       1
 *      / \
 *     2   3
 *    /   / \
 *   4   5   6
 *      /
 *     7
 *     
 * Output: 7 
 * Note: You may assume the tree (i.e., the given root node) is not NULL.

 * @author WinnieZhao
 *
 */
public class FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            root = queue.poll();
            
                if (root.right != null) {
                    queue.add(root.right);
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
        }
        
        return root.val;
    }

    class Solution {
        int height = 0;
        int res = 0;

        public int findBottomLeftValue(TreeNode root) {

            if (root == null) {
                return -1;
            }
            if (root.left == null && root.right == null) {
                return root.val;
            }

            this.helper(root, 1);

            return res;
        }

        private void helper(TreeNode node, int depth) {
            if (height < depth) {
                res = node.val;
                height = depth;
            }

            if (node.left != null) {
                helper(node.left, depth+1);
            }
            if (node.right != null) {
                helper(node.right, depth+1);
            }
        }
    }
    
    public static void main(String[] args) {
        FindBottomLeftTreeValue solution = new FindBottomLeftTreeValue();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left = new TreeNode(5);
        root.right = new TreeNode(6);
        System.out.println(solution.findBottomLeftValue(root));
    }
}
