package LeetCode.Medium;

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

   private TreeNode lastNode = null;
    
   public void flatten(TreeNode root) {
       if (root == null) {
           return;
       }

       if (lastNode != null) {
           lastNode.left = null;
           lastNode.right = root;
       }

       lastNode = root;
       TreeNode right = root.right;
       flatten(root.left);
       flatten(right);
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
