package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
    
    For example, Given
    
             1
            / \
           2   5
          / \   \
         3   4   6
   
    The flattened tree should look like:
    
       1
        \
         2
          \
           3
            \
             4
              \
               5
                \
                 6
             
 * Hints: If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 * 
 * @author WinnieZhao
 *
 */
public class FlattenBinaryTreeToLinkedList {

    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    public class Solution {
        /**
         * @param root: a TreeNode, the root of the binary tree
         * @return: nothing
         */
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.empty()) {
                TreeNode node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }

                // connect
                node.left = null;
                if (stack.empty()) {
                    node.right = null;
                } else {
                    node.right = stack.peek();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
        solution.flatten(root);
        
        while(root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
