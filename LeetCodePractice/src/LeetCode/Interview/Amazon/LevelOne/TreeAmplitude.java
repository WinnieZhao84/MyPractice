package LeetCode.Interview.Amazon.LevelOne;

import LeetCode.Helper.TreeNode;

/**
 * In a binary tree T, a path P is a non-empty sequence of nodes of tree such that,
 * each consecutive node in the sequence is a subtree of its preceding node.
 * In the example tree, the sequences [9, 8, 2] and [5, 8, 12] are two paths,
 * while [12, 8, 2] is not.
 *
 * The amplitude of path P is the maximum difference among values of nodes on path P.
 * The amplitude of tree T is the maximum amplitude of all paths in T.
 * When the tree is empty, it contains no path, and its amplitude is treated as 0.
 *
 * For example.
 * Input:
 *           5
 *        /     \
 *       8       9
 *     /  \     /  \
 *   12   2    8   4
 *            /   /
 *           2   5
 * Output: 7
 *
 * Explanation:
 * The paths [5, 8, 12] and [9, 8, 2] have the maximum amplitude 7.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class TreeAmplitude {

    public static int amplitude(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return max - min;
        }
        if(root.val < min) {
            min = root.val;
        }
        if(root.val > max) {
            max = root.val;
        }
        return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));

    }
}
