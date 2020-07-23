package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * 
 * For example:
 * 
 * Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * 
 *You should return [1, 3, 4].
 *
 *@author WinnieZhao
 *
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
            return result;
        }

        this.rightSideViewHelper(root, result, 0);
        
        return result;
    }
    
    private void rightSideViewHelper(TreeNode node, List<Integer> result, int depth) {
        if (node == null) {
            return;
        }
        if (result.size() == depth) {
            result.add(node.val);
        }
        
        this.rightSideViewHelper(node.right, result, depth+1);
        this.rightSideViewHelper(node.left, result, depth+1);
    }
    
    public List<Integer> rightSideView_BFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                } 
                
                if (i == size-1) {
                    res.add(cur.val);
                }
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);

        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
        System.out.println(solution.rightSideView(root));
    }
}
