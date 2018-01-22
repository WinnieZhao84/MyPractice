package LeetCode.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

 * @author ASUS-PC
 *
 */
public class BinaryTreePaths {
	
	public List<String> binaryTreePaths_DFS(TreeNode root) {

		List<String> result = new ArrayList<String>();
        
        this.binaryTreePaths_helper(root, "", result);
        
        return result;
	}
	
	private void binaryTreePaths_helper(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }
        
        if (!path.isEmpty()) {
            path += "->";
        }
        path += node.val;
        
        if (node.left == null && node.right == null) {
            result.add(path);
        }
        
        if (node.left != null || node.right != null) {
            this.binaryTreePaths_helper(node.left, path, result);
            this.binaryTreePaths_helper(node.right, path, result);
        }
    }
	
	public List<String> binaryTreePaths_BFS(TreeNode root) {
        if (root == null) {
            return new ArrayList<String>();
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        List<String> result = new ArrayList<String>();
        List<String> result2 = new ArrayList<String>();
        result.add(String.valueOf(root.val));
        
        while (!queue.isEmpty()) {
        	int size = queue.size();

        	for (int i=0; i<size; i++) {
        		TreeNode node = queue.poll();
        		String value = result.get(0);
        		
        		if (node.left != null || node.right != null) {
        			if (node.left != null) {
            			queue.offer(node.left);
            			result.add(value + "->" + node.left.val);
        			}
        			if (node.right != null) {
            			queue.offer(node.right);
            			result.add(value + "->" + node.right.val);
        			}
        		}
        		if (node.left == null && node.right == null) {
        			result2.add(value);
        		}

    			result.remove(0);
        	}
        }
        result.addAll(result2);
        return result;
    }

    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();

            if (root == null) {
                return res;
            }

            this.dfs(root, "", res);

            return res;
        }

        private void dfs(TreeNode node, String str, List<String> res) {
            if (node == null) {
                return;
            }

            if (node.left == null && node.right == null) {
                str += node.val;
                res.add(str);
            }

            str += node.val + "->";

            if (node.left != null) {
                this.dfs(node.left, str, res);
            }

            if (node.right != null) {
                this.dfs(node.right, str, res);
            }
        }
    }

	public static void main(String[] args) {
		BinaryTreePaths solution = new BinaryTreePaths();
		
		TreeNode root = new TreeNode(2);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(6);
		
		root.left = left;
		root.right = right;
		
		TreeNode l1 = new TreeNode(5);
		TreeNode l2 = new TreeNode(4);
		TreeNode l3 = new TreeNode(3);
		//TreeNode leftRight = new TreeNode(5);
		right.left = l1;
		l1.left = l2;
		l2.left = l3;
		//left.right = leftRight;
		
		//TreeNode rightRight = new TreeNode(6);
		//right.right = rightRight;
		
		//TreeNode leftLeftLeft = new TreeNode(1);
		//leftLeft.left = leftLeftLeft;
		
		List<String> result = solution.binaryTreePaths_BFS(root);
		for (String s : result) {
			System.out.println(s);
		}
	}
}
