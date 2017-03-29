package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * 536
 *
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * Example: Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *       4
 *     /   \
 *    2     6
 *   / \   /
 *  3   1 5
 *
 *  Note: There will only be '(', ')', '-' and '0' ~ '9' in the input string.

 * Created by WinnieZhao on 2017/3/28.
 */
public class ConstructBinaryTreeFromString {

    private int index = 0;
    public TreeNode str2tree(String s) {

        if (s == null || s.isEmpty()) {
            return  null;
        }

        int len = s.length();
        int sign = 1;
        char ch;
        int val = 0;

        if (s.charAt(index)=='-') {
            sign = -1;
            index++;
        }

        while (index<len && (ch=s.charAt(index))<='9'&&ch>='0'){
            val*=10;
            val+=ch -'0';
            index++;
        }

        TreeNode root = new TreeNode(sign*val);

        if (index>=len || s.charAt(index)==')'){
            index++;
            return root; //have no child
        } //here now index is pointing to a '('

        index++; // Here now index pointing to a number
        root.left = this.str2tree(s);

        if (index>=len || s.charAt(index)==')'){
            index++;
            return root; //have no child
        } //here now index is pointing to a '('

        index++; // Here now index pointing to a number
        root.right = this.str2tree(s);

        if(index>=len||s.charAt(index)==')'){
            index++;
        }
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromString solution = new ConstructBinaryTreeFromString();
        TreeNode root = solution.str2tree("4(2(3)(1))(6(5))");
    }
}
