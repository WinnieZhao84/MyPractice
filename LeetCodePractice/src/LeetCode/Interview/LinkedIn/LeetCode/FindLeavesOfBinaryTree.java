package LeetCode.Interview.LinkedIn.LeetCode;

import LeetCode.Helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves,
 * repeat until the tree is empty.
 *
 * Example: Given binary tree
 *    1
 *   / \
 *  2   3
 * / \
 *4   5
 *
 * Returns [4, 5, 3], [2], [1].
 *
 * Explanation: Removing the leaves [4, 5, 3] would result in this tree:
 *
 *   1
 *  /
 * 2
 *
 * Now removing the leaf [2] would result in this tree:
 * 1
 *
 * Now removing the leaf [1] would result in the empty tree:
 * []
 *
 * Returns [4, 5, 3], [2], [1].
 *
 * Created by WinnieZhao on 2017/3/27.
 */
public class FindLeavesOfBinaryTree {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        this.helper(root);

        return result;
    }

    /**
     * Find the deepest depth between its left and right for the node
     *
     *
     * @param root
     * @return
     */
    private int helper(TreeNode root) {

        if (root == null) {
            return -1;
        }

        int left = this.helper(root.left);
        int right = this.helper(root.right);

        int height = Math.max(left, right) + 1;

        if (height >= result.size()) {
            result.add(new ArrayList<>());
        }

        result.get(height).add(root.val);

        return height;
    }

    public static void main(String[] args) {
        FindLeavesOfBinaryTree solution = new FindLeavesOfBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        List<List<Integer>> res = solution.findLeaves(root);

        res.stream().forEach(l -> System.out.println(l.toString()));
    }
}
