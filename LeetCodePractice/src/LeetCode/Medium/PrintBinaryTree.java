package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.*;

/**
 * Print a binary tree in an m*n 2D string array following these rules:
 *
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
 * The column and the row where the root node belongs will separate the rest space into two parts
 * (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part
 * and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part
 * should have the same size. Even if one subtree is none while the other is not, you don't need to print
 * anything for the none subtree but still need to leave the space as large as that for the other subtree.
 * However, if two subtrees are none, then you don't need to leave space for both of them.
 *
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 * Example 1:
 * Input:
 *    1
 *   /
 *  2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * Example 2:
 * Input:
 *     1
 *    / \
 *   2   3
 *   \
 *    4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * Example 3:
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 *  Note: The height of binary tree is in the range of [1, 10].
 *
 * Created by WinnieZhao on 8/9/2017.
 */
public class PrintBinaryTree {

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        if (root == null) {
            return null;
        }

        int depth = this.getDepth(root);
        int size = (1 << depth) - 1;

        String[][] res = new String[depth][size];
        for (String[] arr : res) {
            Arrays.fill(arr, "");
        }

        this.fill(res, root, 0, 0, size);

        for (String[] arr:res) {
            result.add(Arrays.asList(arr));
        }
        return result;
    }

    private void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null) {
            return;
        }

        res[i][(l + r) / 2] = "" + root.val;

        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(this.getDepth(root.left), this.getDepth(root.right)) + 1;
    }
}
