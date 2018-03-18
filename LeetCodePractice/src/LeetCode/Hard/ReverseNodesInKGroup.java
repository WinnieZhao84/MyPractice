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

        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode begin = dummy;

        int i=0;

        while (head != null) {
            i++;

            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            }
            else {
                head = head.next;
            }
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode cur = begin.next;
        ListNode pre = begin;

        ListNode first = cur;

        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        begin.next = pre;
        first.next = cur;

        return first;
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
