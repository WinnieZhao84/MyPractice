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
		if (left == null || right == null) {
			return left == right;
		}

		if (left.val != right.val ) {
			return false;
		}

		return this.isSymmetricHelper(left.left, right.right) && this.isSymmetricHelper(left.right, right.left);
	}
    
    public boolean isSymmetric_Iteratively(TreeNode root) {
		if (root == null) {
			return true;
		}

		Queue<TreeNode> leftQueue = new LinkedList<>();
		Queue<TreeNode> rightQueue = new LinkedList<>();

		leftQueue.add(root.left);
		rightQueue.add(root.right);

		while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
			int leftSize = leftQueue.size();
			int rightSize = rightQueue.size();

			if (leftSize != rightSize) {
				return false;
			}

			for (int i= 0; i<leftSize; i++) {
				TreeNode left = leftQueue.poll();
				TreeNode right = rightQueue.poll();

				if (left == null && right == null) {
					continue;
				}
				else if (left == null || right == null) {
					return false;
				}

				if (left.val != right.val) {
					return false;
				}

				leftQueue.add(left.left);
				leftQueue.add(left.right);

				rightQueue.add(right.right);
				rightQueue.add(right.left);
			}
		}

		return leftQueue.isEmpty() && rightQueue.isEmpty();
    }
}
