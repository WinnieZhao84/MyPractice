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
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        this.levelOrder(root, 0, result);
        Collections.reverse(result);
        
        return result;
    }

    private void levelOrder(TreeNode root, int depth, List<List<Integer>> result) {
        if (root != null) {
            if (result.size() == 0 || result.size() <= depth) {
                List<Integer> sameLevelList = new ArrayList<Integer>();
                sameLevelList.add(root.val);
                result.add(sameLevelList);
            }
            else {
                result.get(depth).add(root.val);
            }
            
            levelOrder(root.left, depth+1, result);
            levelOrder(root.right, depth+1, result);
        }
    }
}
