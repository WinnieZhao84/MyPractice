package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.Helper.TreeLinkNode;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *              1
 *             /  \
 *            2    3
 *           / \    \
 *          4   5    7
 *          
 * After calling your function, the tree should look like:
 *           1 -> NULL
 *          /  \
 *         2 -> 3 -> NULL
 *        / \    \
 *       4-> 5 -> 7 -> NULL
 *          
 * @author WinnieZhao
 *
 */
public class PopulatingNextRightPointersInEachNodeII {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            TreeLinkNode next = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();

                node.next = next;
                next = node;

                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
    }
    
    public void connect_constantSpace(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;

        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }

            root = root.next;

            if (root == null) {
                pre = dummyHead;
                root = pre.next;
                dummyHead.next = null;
            }
        }
    }
    
    public static void main(String[] args) {
        PopulatingNextRightPointersInEachNodeII solution = new PopulatingNextRightPointersInEachNodeII();
        
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.right.right = new TreeLinkNode(5);
        //root.left.left.left = new TreeLinkNode(4);
        //root.left.left.right = new TreeLinkNode(4);
        
       solution.connect_constantSpace(root);


    }
}
