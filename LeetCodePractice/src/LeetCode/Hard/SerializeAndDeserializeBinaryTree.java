package LeetCode.Hard;

import LeetCode.Helper.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 * in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
 * another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 *
 *
 * For example, you may serialize the following tree
 *
 *   1
 *  / \
 * 2   3
 *    / \
 *   4   5
 *
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * Created by WinnieZhao on 2017/6/19.
 */
public class SerializeAndDeserializeBinaryTree {

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        this.buildString(root, sb);

        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        }
        else {
            sb.append(node.val).append(spliter);

            this.buildString(node.left, sb);
            this.buildString(node.right, sb);
        }

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));

        return buildTree(nodes);
    }

    private TreeNode buildTree(LinkedList<String> nodes) {
        String value = nodes.remove();
        if (value.equals(NN)) {
            return null;
        }
        else {
            TreeNode node = new TreeNode(Integer.valueOf(value));
            node.left = this.buildTree(nodes);
            node.right = this.buildTree(nodes);

            return node;
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode p2 = new TreeNode(2);
        TreeNode p8 = new TreeNode(8);

        root.left = p2;
        root.right = p8;

        TreeNode p0 = new TreeNode(0);
        TreeNode p4 = new TreeNode(4);

        p2.left = p0;
        p2.right = p4;

        TreeNode p3 = new TreeNode(3);
        TreeNode p5 = new TreeNode(5);

        p4.left = p3;
        p4.right = p5;

        TreeNode p7 = new TreeNode(7);
        TreeNode p9 = new TreeNode(9);

        p8.left = p7;
        p8.right = p9;

        SerializeAndDeserializeBinaryTree solution = new SerializeAndDeserializeBinaryTree();
        System.out.println(solution.serialize(root));
    }
}
