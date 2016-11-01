package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeLinkNode;

/**
 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
    
   Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

   Initially, all next pointers are set to NULL.
   
   Note: 
   You may only use constant extra space.
   You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
   
   For example, Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
    
   After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 * 
 * @author WinnieZhao
 *
 */
public class PopulatingNextRightPointersInEachNode {

    public void connect(TreeLinkNode root) {
        
        if (root == null) {
            return;
        }
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            TreeLinkNode pre = null;
            TreeLinkNode node = null;
            
            for (int i=0; i<=size-1; i++) {
                node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                
                if (node != null && queue.isEmpty()) {
                    node.next = null;
                }
                
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
                pre = node;
            }
        }
    }
    
    public void connect_better(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        while (root != null) {
            TreeLinkNode p = root;
            while (p != null && p.left != null) {
                p.left.next = p.right;
                p.right.next = p.next == null ? null : p.next.left;
                p = p.next;
            }
            root = root.left;
        }

    }
    
    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);
        
        PopulatingNextRightPointersInEachNode solution = new PopulatingNextRightPointersInEachNode();
        
        solution.connect_better(root);
        
        System.out.println(root.val + "->" + root.next);
        
        System.out.println(root.left.val + "->" +root.left.next.val);
        System.out.println(root.right.val + "->" +root.right.next);
        
        System.out.println(root.left.left.val + "->" +root.left.left.next.val);
        System.out.println(root.left.right.val + "->" +root.left.right.next);
        System.out.println(root.right.left.val + "->" +root.right.left.next.val);
        System.out.println(root.right.right.val + "->" +root.right.right.next);
    }
}
