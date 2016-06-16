package LeetCode.Easy;
import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeNode;


/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

 * But the following is not:
    1
   / \
  2   2
   \   \
   3    3

 * Note:
 * Bonus points if you could solve it both recursively and iteratively.

 * @author ASUS-PC
 *
 */
public class SymmetricTree {
	
	/**
	 * Method Process Recursively
	 * @param root
	 * @return
	 */
    public boolean isSymmetric_Recursively(TreeNode root) {
        
    	if (root == null) {
    		return true;
    	}
    	
    	return this.isSymmetricHelper(root.left, root.right);
    	
    }
    
    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
    	if (left == null && right == null) {
    		return true;
    	}
    	else if (left == null || right == null) {
    		return false;
    	}
    	else {
    		if (left.val != right.val) {
    			return false;
    		}
    		else {
    			return this.isSymmetricHelper(left.left, right.right) && this.isSymmetricHelper(left.right, right.left);
    		}
    	}
    }
    
    public boolean isSymmetric_Iteratively(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	Queue<TreeNode> q1=new LinkedList<>();
    	Queue<TreeNode> q2=new LinkedList<>();
    	
        q1.add(root.left); 
        q2.add(root.right);
        
        while(!q1.isEmpty() && !q2.isEmpty()) {
            int size1=q1.size();
            int size2=q2.size();
            if(size1!=size2) return false;
            
            for(int i=0; i<size1; i++) {
                TreeNode current1=q1.remove();
                TreeNode current2=q2.remove();
                
                if(current1==null && current2==null) 
                	continue;
                if(current1==null || current2==null) 
                	return false; 
                if(current1.val!=current2.val) 
                	return false;
                
                q1.add(current1.left);
                q1.add(current1.right);
                q2.add(current2.right);
                q2.add(current2.left);
            }
        }
        return q1.isEmpty() && q2.isEmpty();
    }
}
