package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. 
 * You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * @author WinnieZhao
 *
 */

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));
public class SerializeAndDeserializeBST_Iterator {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String result = null;
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        StringBuffer buffer = new StringBuffer();
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    buffer.append("");
                }
                else {
                    buffer.append(node.val);
                }
                buffer.append(",");
                
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        
        result = buffer.toString();
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        
        String[] array = data.split(",");
        int rootVal = Integer.valueOf(array[0]);
        TreeNode root = new TreeNode(rootVal);
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            for (int i=1; i<=array.length-1; i++) {
                TreeNode node = queue.poll();
                
                if (array[i].isEmpty()) {
                    node.left = null;
                }
                else {
                    node.left = new TreeNode(Integer.valueOf(array[i]));
                    queue.add(node.left);
                }
                i = i + 1;
                
                if (i > array.length-1) break;
                
                if (array[i].isEmpty()) {
                    node.right = null;
                }
                else {
                    node.right = new TreeNode(Integer.valueOf(array[i]));
                    queue.add(node.right);
                }
            }
            break;
        }
        
        return root;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        SerializeAndDeserializeBST_Iterator solution = new SerializeAndDeserializeBST_Iterator();
        String data = solution.serialize(root);
        System.out.println(data);
        
        TreeNode node = solution.deserialize(data);
        System.out.println(node.val);
        System.out.println(node.left.val);
        System.out.println(node.right.val);
        System.out.println(node.left.left);
        System.out.println(node.left.right);
        System.out.println(node.right.left.val);
        System.out.println(node.right.right.val);
    }
}
