package LeetCode.Medium;

import LeetCode.Helper.ListNode;
import LeetCode.Helper.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * @author WinnieZhao
 *
 */
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            TreeNode node = new TreeNode(head.val);
            return node;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        pre.next = null;

        ListNode left = head;
        ListNode right = slow.next;

        TreeNode root = new TreeNode(slow.val);
        root.left = this.sortedListToBST(left);
        root.right = this.sortedListToBST(right);
        
        return root;
    }
    
    public static void main(String[] args) {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        
        TreeNode root = solution.sortedListToBST(head);
        System.out.println(root);
    }
    
}
