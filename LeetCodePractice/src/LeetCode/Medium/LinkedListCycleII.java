package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * Note: Do not modify the linked list.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 * @author WinnieZhao
 *
 */
public class LinkedListCycleII {
    
    public ListNode detectCycle(ListNode head) {
        
        if (head == null || head.next == null) {
            return null;   // no circle
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                while (head != fast) {
                    fast = fast.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null; // no circle
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        
        head.next = next;
        next.next = head;
        
        LinkedListCycleII solution = new LinkedListCycleII();
        if (solution.detectCycle(head) != null) {
            System.out.println(solution.detectCycle(head).val);
        } else {
            System.out.println(solution.detectCycle(head));
        }
    }
}
