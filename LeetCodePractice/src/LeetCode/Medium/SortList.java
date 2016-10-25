package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author WinnieZhao
 *
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        return this.sortListHelper(head);
    }
    
    private ListNode sortListHelper(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        pre.next = null;
        
        ListNode l1 = this.sortListHelper(head);
        ListNode l2 = this.sortListHelper(slow);
        
        return this.merge(l1, l2);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        if (l1.val < l2.val) {
            l1.next = this.merge(l1.next, l2);
            return l1;
        }
        else {
            l2.next = this.merge(l1, l2.next);
            return l2;
        }
    }
    
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(4);
        ListNode third= new ListNode(3);
        ListNode fourth = new ListNode(2);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(2);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        
        SortList solution = new SortList();
        ListNode head = solution.sortList(first);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
