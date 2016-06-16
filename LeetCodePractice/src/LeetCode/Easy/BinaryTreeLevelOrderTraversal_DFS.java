package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * return its level order traversal as:
 * [
 *  [3],
 *  [9,20],
 *  [15,7]
 * ]
 * @author WinnieZhao
 *
 */
public class BinaryTreeLevelOrderTraversal_DFS {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        this.levelOrder(root, 0, result);
        
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
