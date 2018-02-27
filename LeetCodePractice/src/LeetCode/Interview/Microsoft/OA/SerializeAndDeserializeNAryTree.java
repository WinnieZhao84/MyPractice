package LeetCode.Interview.Microsoft.OA;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Example:
 *         1
 *       / | \
 *      2  3  4
 *     / \     \
 *    5  6      7
 *
 * https://discuss.leetcode.com/topic/25/serialize-and-deserialize-an-n-ary-tree/17?page=1
 * Created by WinnieZhao on 2/24/2018.
 */
public class SerializeAndDeserializeNAryTree {

    class TreeNode {
        int val;
        List<TreeNode> children;

        public TreeNode(int val, List<TreeNode> children) {
            this.val = val;
            this.children = children;
        }
    }

    public TreeNode deserialize(String input)  {
        if (input == null || input.isEmpty()) {
            return null;
        }

        StringBuilder in = new StringBuilder(input);

        return this.buildTree(in);
    }

    private TreeNode buildTree(StringBuilder in) {
        if (in == null || in.toString().isEmpty()) {
            return null;
        }

        TreeNode root = null;
        if (in.charAt(0) == '(') {
            in.deleteCharAt(0);

            root = new TreeNode(in.charAt(0) - '0', new ArrayList<>());

            in.deleteCharAt(0);
            while (in.charAt(0) !=  ')') {
                root.children.add(buildTree(in));
            }
            in.deleteCharAt(0);
        }

        return root;
    }

    // Return serialized string as (1(2)(3(5)(6))(4(7)))
    public String serialize(TreeNode root)  {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        this.serializeTree(root, sb);

        return sb.toString();
    }

    private void serializeTree(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append("(");
        sb.append(root.val);

        for (TreeNode child : root.children) {
            this.serializeTree(child, sb);
        }

        sb.append(")");
    }

    public static void main(String[] args) {
        SerializeAndDeserializeNAryTree solution = new SerializeAndDeserializeNAryTree();

        TreeNode root = solution.deserialize("(1(2)(3(5)(6))(4(7)))");
        System.out.println(solution.serialize(root));
    }
}
