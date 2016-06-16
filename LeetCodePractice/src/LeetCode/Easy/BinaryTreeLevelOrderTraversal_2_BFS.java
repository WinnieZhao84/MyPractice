package LeetCode.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

 * @author WinnieZhao
 *
 */
public class BinaryTreeLevelOrderTraversal_2_BFS {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);

        while(!nodeQueue.isEmpty()) {

            List<Integer> sameLevelList = new ArrayList<Integer>();
            int size = nodeQueue.size();
            
            for (int i = 0; i <= size-1; i++) {  
                TreeNode node = nodeQueue.poll();
                sameLevelList.add(node.val);
                
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                }
                
            }
            
            result.add(0, sameLevelList);
            
        }
        
        return result;
    }
    
}
