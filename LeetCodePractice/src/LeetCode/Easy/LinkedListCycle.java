package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * @author WinnieZhao
 *
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && slow != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            }
            else {
                return false;
            }
            slow = slow.next;
            
            if (fast != null && slow != null && fast.val == slow.val) {
                return true;
            }
        }
        
        return false;
    }
}
