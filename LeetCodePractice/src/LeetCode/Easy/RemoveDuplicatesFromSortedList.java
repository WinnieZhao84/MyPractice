package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

 * @author ASUS-PC
 *
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = head;

        while (p != null) {
            ListNode next = p.next;

            while (next != null && next.val == p.val) {
                next = next.next;
            }

            p.next = next;
            p = p.next;
        }

        return head;
    }


    public ListNode deleteDuplicates_better(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }
        return head;
    }
}
