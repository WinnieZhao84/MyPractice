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
        while (!queue.isEmpty()) {
            int size = queue.size();

            TreeLinkNode node = null;
            
            for (int i=0; i<size; i++) {
                node = queue.poll();
                
                if (node != null) {
                    while(queue.peek() == null) {
                        queue.poll();
                        i++;
                        if (i >= size - 1) {
                            break;
                        }
                    }
                    if (i >= size - 1) {
                        node.next = null;
                    }
                    else {
                        node.next = queue.peek();
                    }
                    
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
    }
    
    public void connect_constantSpace(TreeLinkNode root) {
        TreeLinkNode tempChild = new TreeLinkNode(0);
        
        while (root != null) {
            TreeLinkNode currentChild = tempChild;
            
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
            tempChild.next = null;
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
