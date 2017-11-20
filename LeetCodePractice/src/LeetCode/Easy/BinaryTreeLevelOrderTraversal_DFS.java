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
        
        this.levelOrder(result, root, 0);
        
        return result;
    }

    private void levelOrder(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if (height >= res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(height).add(root.val);
        this.levelOrder(res, root.left, height+1);
        this.levelOrder(res, root.right, height+1);
    }
}
