package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 
 * @author WinnieZhao
 *
 */
public class AddTwoNumbers {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        else if (l2 == null) {
            l2 = new ListNode(0);
        }
        else if (l1 == null) {
            l1 = new ListNode(0);
        }
        
        if (l1.val + l2.val >= 10) {
            ListNode node = new ListNode(l1.val + l2.val - 10);
            
            node.next = this.addTwoNumbers(this.addTwoNumbers(l1.next, l2.next), new ListNode(1));
            return node;
        }
        else {
            ListNode node = new ListNode(l1.val + l2.val);
            
            node.next = this.addTwoNumbers(l1.next, l2.next);
            return node;
        }
    }
}
