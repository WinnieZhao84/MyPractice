package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
 * you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *     Example 1:
 *       1
 *      / \
 *     2   3
 *    /   / \
 *   4   2   4
 *      /
 *     4
 * The following are two duplicate subtrees:
 *    2
 *   /
 *  4
 *
 * and
 * 4
 * Therefore, you need to return above trees' root in the form of a list.
 *
 * Created by WinnieZhao on 7/31/2017.
 */
public class FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        helper(root, new HashMap<>(), res);
        return res;
    }

    private String helper(TreeNode node, Map<String, Integer> map, List<TreeNode> res) {
        if (node == null) {
            return "#";
        }

        String key = node.val + "," + helper(node.left, map, res) + "," + helper(node.right, map, res);

        if (map.getOrDefault(key, 0) == 1) {
            res.add(node);
        }
        map.put(key, map.getOrDefault(key, 0) + 1);

        return key;
    }
}
