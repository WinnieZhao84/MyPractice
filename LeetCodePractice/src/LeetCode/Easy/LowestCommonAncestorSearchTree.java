package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes v 
 * and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself)."

         _______3______
        /              \
    ___5__           ___1__
   /      \         /      \
   6       2        0       8
         /  \
        7   4

 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3.
 * Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition
 * @author ASUS-PC
 *
 */
public class LowestCommonAncestorSearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    	if (root == null) {
    		return null;
    	}
    	if (root == p) {
    		return root;
    	}
    	if (root == q) {
    		return root;
    	}
    	
    	TreeNode left = this.lowestCommonAncestor(root.left, p, q);
    	TreeNode right = this.lowestCommonAncestor(root.right, p, q);
    	
    	if (left != null && right != null) {
    		return root;
    	}
    	else {
    		return left == null? right : left;
    	}
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(6);
    	TreeNode p2 = new TreeNode(2);
    	TreeNode p8 = new TreeNode(8);
    	
    	root.left = p2;
    	root.right = p8;
    	
    	TreeNode p0 = new TreeNode(0);
    	TreeNode p4 = new TreeNode(4);
    	
    	p2.left = p0;
    	p2.right = p4;
    	
    	TreeNode p3 = new TreeNode(3);
    	TreeNode p5 = new TreeNode(5);
    	
    	p4.left = p3;
    	p4.right = p5;
    	
    	TreeNode p7 = new TreeNode(7);
    	TreeNode p9 = new TreeNode(9);
    	
    	p8.left = p7;
    	p8.right = p9;
    	
    	LowestCommonAncestorSearchTree solution = new LowestCommonAncestorSearchTree();
    	
    	TreeNode result = solution.lowestCommonAncestor(root, p0, p7);
    	System.out.print(result.val);
    }
}
