package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

 * @author ASUS-PC
 *
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	
    	return this.isSameTreeHelper(p, q);
    }
    
    private boolean isSameTreeHelper(TreeNode p, TreeNode q) {

    	if (p == null && q == null) {
    		return true;
    	}
    	if (p == null || q == null) {
    		return false;
    	}
    	if (p.val == q.val) {
        	return this.isSameTreeHelper(p.left, q.left) && this.isSameTreeHelper(p.right, q.right);
    	}
    	else {
    		return false;
    	}

    }
    
    public static void main(String[] args) {
    	SameTree solution = new SameTree();
    	
    	TreeNode p = new TreeNode(1);
    	p.left = null;
    	
    	TreeNode p1 = new TreeNode(1);
    	p.right = p1;
    			
    	TreeNode q = new TreeNode(1);
    	q.left = null;
    	
    	TreeNode q1 = new TreeNode(1);
    	q.right = q1;

    	System.out.print(solution.isSameTree(p,q));
    }
}
