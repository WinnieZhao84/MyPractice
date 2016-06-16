package LeetCode.Easy;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 * @author ASUS-PC
 *
 */
public class MinimumDepthBinaryTree {
	
	public int minDepth_recursively(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	return this.minDepthHelper(root, 1);
	}
	
	private int minDepthHelper(TreeNode node, int depth) {
		if (node == null) return Integer.MAX_VALUE;
		if (node.left == null && node.right == null) {
			return depth;
		}
		
		return Math.min(this.minDepthHelper(node.left, depth+1), this.minDepthHelper(node.right, depth+1));
	}

    public int minDepth_iternatively(TreeNode root) {
        
    	if (root == null) {
    		return 0;
    	}
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();

    	queue.offer(root);
    	int depth = 0;
    	while(!queue.isEmpty()) {
       		depth++;
    		int size = queue.size();
    		
    		for (int i=0; i<size; i++) {
    			TreeNode node = queue.poll();
    			
    			if (node.left == null && node.right == null) {
    				return depth;
    			}
    			if (node.left != null) {
    				queue.offer(node.left);
    			}
                if (node.right != null) {
                	queue.offer(node.right);
                }
    		}
    	}
    	return depth;
    }
    
    public static void main(String[] args) {
    	MinimumDepthBinaryTree solution = new MinimumDepthBinaryTree();
        
    	TreeNode root = new TreeNode(1);
    	TreeNode left = new TreeNode(2);
    	//TreeNode right = new TreeNode(3);
    	root.left = left;
    	//root.right = right;
    	
    	//TreeNode leftLeft = new TreeNode(4);
    	//left.left = leftLeft;
    	
    	//TreeNode leftRight = new TreeNode(4);
    	//left.right = leftRight;
    	
        System.out.println(solution.minDepth_iternatively(root));
    }
}
