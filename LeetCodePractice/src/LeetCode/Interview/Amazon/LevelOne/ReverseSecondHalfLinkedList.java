package LeetCode.Interview.Amazon.LevelOne;

import LeetCode.Helper.ListNode;

/**
 * 2->1->3->4->5->6->7->8 变成 2->1->3->4->8->7->6->5;
 * 如果总是为奇数，中间的也要变 5->7->8->6->3->4->2 变成 5->7->8->2->4->3->6
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class ReverseSecondHalfLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode pre = null;
        ListNode cur = slow.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        slow.next = pre;

        return head;
    }
}
