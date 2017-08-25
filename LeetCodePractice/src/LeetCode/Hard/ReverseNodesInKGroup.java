package LeetCode.Hard;

import LeetCode.Helper.ListNode;

import java.util.List;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5

 * Created by WinnieZhao on 2017/6/20.
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;

        int count = 0;

        while (cur != null) {
            count++;
            ListNode next = cur.next;
            if (count == k) {
                System.out.println("pre:" + pre.val);
                System.out.println("next:" + next.val);

                pre = reverse(pre, next);
                count = 0;
            }
            cur = next;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode pre, ListNode end) {
        if (pre==null || pre.next==null) {
            return pre;
        }
        ListNode head = pre.next;
        ListNode cur = pre.next.next;

        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        head.next = end;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        ListNode res = solution.reverseKGroup(head, 2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
