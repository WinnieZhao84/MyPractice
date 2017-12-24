package LeetCode.Medium;

import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 * @author WinnieZhao
 *
 */
public class BinarySearchTreeIterator {

    TreeNode root = null;
    int height = 0;
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    /**
     * Your BSTIterator will be called like this:
     * BSTIterator i = new BSTIterator(root);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public BinarySearchTreeIterator(TreeNode root) {
        this.root = root;
        constructStack(root);
    }
    
    private void constructStack(TreeNode node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode minNode = stack.pop();

        if (minNode != null) {
            constructStack(minNode.right);
        }

        return minNode.val;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = this.getHeight(root.left);
        int right = this.getHeight(root.right);
        
        return 1 + (left == 0 ? right : left);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        
        root.left = new TreeNode(6);
        root.right = new TreeNode(9);
        
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.left.left.right = new TreeNode(6);

        BinarySearchTreeIterator solution = new BinarySearchTreeIterator(root);
        System.out.println(solution.getHeight(root));
        System.out.println(solution.stack);
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
    }
}
