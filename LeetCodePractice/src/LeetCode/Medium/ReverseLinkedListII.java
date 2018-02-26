package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example: 
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * 
 * Note:
 * Given m, n satisfy the following condition:
 * 1 <= m <= n <= length of list.
 * 
 * @author WinnieZhao
 *
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre1 = dummy;
        ListNode cur1 = head;
        for (int i=0; i<m-1; i++) {
            pre1 = cur1;
            cur1 = cur1.next;
        }

        ListNode pre2 = null;
        ListNode cur2 = cur1;
        for (int i=m; i<=n; i++) {
            ListNode next = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = next;
        }

        pre1.next = pre2;
        cur1.next = cur2;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);

        ReverseLinkedListII solution = new ReverseLinkedListII();
        System.out.println(solution.reverseBetween(head, 1, 1));
    }
    
}
