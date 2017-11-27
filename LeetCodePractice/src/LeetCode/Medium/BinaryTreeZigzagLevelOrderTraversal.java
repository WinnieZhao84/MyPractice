package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
 * then right to left for the next level and alternate between).
 * 
 * For example: Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
     
 * return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]
 * @author WinnieZhao
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder_BFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 0;
        queue.add(root);
        
        List<Integer> list = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<=size-1; i++) {
                TreeNode node = queue.poll();
                
                if (node != null) {
                    if (level % 2 == 0) {
                        list.add(node.val);
                    }
                    else {
                        list.add(0, node.val);
                    }
                    
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            
            if (!list.isEmpty()) {
                result.add(list);
                list = new ArrayList<>();
                level++;
            }
        }
        
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder_DFS(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        this.helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if (height >= res.size()) {
            res.add(new ArrayList<>());
        }

        if (height % 2 == 0) {
            res.get(height).add(root.val);
        }
        else {
            res.get(height).add(0, root.val);
        }

        this.helper(res, root.left, height+1);
        this.helper(res, root.right, height+1);
    }
    
    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal solution = new BinaryTreeZigzagLevelOrderTraversal();
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        List<List<Integer>> result = solution.zigzagLevelOrder_DFS(root);
        System.out.println(result);
    }
}
