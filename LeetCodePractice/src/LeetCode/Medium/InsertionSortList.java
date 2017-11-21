package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Sort a linked list using insertion sort.
 * 
 * @author WinnieZhao
 *
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);

        ListNode pre = dummy;
        ListNode cur = head;
        
        while (cur != null) {
            pre = dummy;
            
            while(pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
        ListNode first = new ListNode(4);
        ListNode second = new ListNode(6);
        ListNode third= new ListNode(5);
        ListNode fourth = new ListNode(2);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(2);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        
        InsertionSortList solution = new InsertionSortList();
        ListNode head = solution.insertionSortList(first);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
    
}
