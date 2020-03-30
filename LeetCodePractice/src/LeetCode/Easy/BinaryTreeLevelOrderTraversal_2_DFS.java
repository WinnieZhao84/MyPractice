package LeetCode.Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

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
public class BinaryTreeLevelOrderTraversal_2_DFS {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        this.dfs(result, root, 0);

        return result;
    }
    
    private void dfs(List<List<Integer>> result, TreeNode node, int height) {
        if (node == null) {
            return;
        }
        
        if (height >= result.size()) {
            result.add(0, new ArrayList<>());
        }

        
        dfs(result, node.left, height + 1);
        dfs(result, node.right, height + 1);
        
        result.get(result.size()-height-1).add(node.val);
    }
}
