package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

public class SerializeAndDeserializeBST_Recursive {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        this.preorder(root, sb);

        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        LinkedList<String> values = new LinkedList(Arrays.asList(data.split(",")));

        TreeNode root = this.getNode(values);

        return root;
    }

    private TreeNode getNode(LinkedList<String> values) {
        String rootVal = values.remove();
        if (rootVal.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(rootVal));

        root.left = getNode(values);
        root.right = getNode(values);

        return root;
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
            sb.append(",");
            return;
        }

        sb.append(root.val);
        sb.append(",");

        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        SerializeAndDeserializeBST_Recursive solution = new SerializeAndDeserializeBST_Recursive();
        String data = solution.serialize(root);
        System.out.println(data);

        TreeNode node = solution.deserialize(data);
        System.out.println(node);
    }
}
