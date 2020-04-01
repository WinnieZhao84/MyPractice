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
	
    /**
     * DFS solution
     */
	public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        else if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}
	
/**
 * BFS is much better here, rather than a DFS approach.
 * Sure, the solution here is short in terms of lines of code and looks nice, but it's far from optimal.
 * If you have a tree where say the root's left subtree has a depth of 500 and the right subtree has a depth of 1, 
 * the code is going to traverse all the way down the 500 left subtree first before finally traversing the right 
 * subtree with a depth of 1 and figuring out "d'oh!" that's the min depth.
 */
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
