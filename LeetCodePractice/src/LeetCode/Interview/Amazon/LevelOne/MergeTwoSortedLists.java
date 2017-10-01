package LeetCode.Interview.Amazon.LevelOne;

import LeetCode.Helper.ListNode;

/**
 * Same as LeetCode
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = this.mergeTwoLists(l1.next, l2);

        }
        else {
            head = l2;
            head.next = this.mergeTwoLists(l1, l2.next);
        }

        return head;
    }
}
