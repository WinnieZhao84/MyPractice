package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * 
 * Note: 
 * You may assume k is always valid, 1 <= k <= BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * 
 * 
 * Hint:
 * 
 * 1) Try to utilize the property of a BST.
 * 2) What if you could modify the BST node's structure?
 * 3) The optimal runtime complexity is O(height of BST).
 * 
 * @author WinnieZhao
 *
 */
public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> nodes = this.inorderTraversal(root, k);
        
        return nodes.get(k-1).val;
    }
    
    private List<TreeNode> inorderTraversal(TreeNode root, int k) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        this.inorderTraversalHelper(root, nodes, k);

        return nodes;
    }
    
    private void inorderTraversalHelper(TreeNode root, List<TreeNode> nodes, int k) {
        if (nodes.size() == k) {
            return;
        }
        if (root.left != null) {
            this.inorderTraversalHelper(root.left, nodes, k);
        }
        
        nodes.add(root);
        
        if (root.right != null) {
            this.inorderTraversalHelper(root.right, nodes, k);
        }
    }
    
    /**
     * Binary Search (dfs): most preferable
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest_binarySearch(TreeNode root, int k) {
        int count = countNodes(root.left);
        
        if (k <= count) {
            return kthSmallest_binarySearch(root.left, k);
        } 
        else if (k > count + 1) {
            return kthSmallest_binarySearch(root.right, k-1-count); // 1 is counted as current node
        }
        
        return root.val;
    }
    
    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
    
    public static void main(String[] args) {
        KthSmallestElementInBST solution = new KthSmallestElementInBST();
        
        TreeNode root = new TreeNode(5);
        
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        
        root.left = left;
        root.right = right;
        
        TreeNode leftLeft = new TreeNode(1);
        TreeNode leftRight = new TreeNode(4);
        left.left = leftLeft;
        left.right = leftRight;
        
        TreeNode rightLeft = new TreeNode(1);
        TreeNode rightRight = new TreeNode(8);
        right.left = null;
        right.right = rightRight;
        
        System.out.println(solution.kthSmallest(root, 5));
        System.out.println(solution.kthSmallest(root, 4));
    }
    
}
