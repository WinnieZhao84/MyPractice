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
        if (head == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        for (int i=0; i<m-1; i++) {
            pre = pre.next;
        }
        
        // 1 - 2 - 3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5
        ListNode start = pre.next;
        ListNode then = start.next;
        
        // first reversing : dummy->1 - 3 - 2 - 4 - 5;   pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5;   pre = 1, start = 2, then = 5 (finish)
        for (int i=0; i<n-m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        
        return dummy.next;
    }
    
}
