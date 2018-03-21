package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * 
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * 
 * @author WinnieZhao
 *
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = head;


        while (fast != null) {

            while (fast != null && fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }

            if (slow.next == fast) {
                slow = slow.next;
            }
            else {
                slow.next = fast.next;
            }

            fast = fast.next;
        }

        return dummy.next;
    }
    
    public static void main(String[] args) {
/*        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third_1 = new ListNode(3);
        ListNode third_2 = new ListNode(3);
        ListNode fourth_1 = new ListNode(4);
        ListNode fourth_2 = new ListNode(4);
        ListNode fifth = new ListNode(5);
        
        first.next = second;
        second.next = third_1;
        third_1.next = third_2;
        third_2.next = fourth_1;
        fourth_1.next = fourth_2;
        fourth_2.next = fifth;
        
        RemoveDuplicatesFromSortedListII solution = new RemoveDuplicatesFromSortedListII();
        ListNode head = solution.deleteDuplicates(first);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }*/
        
        
        ListNode first = new ListNode(1);
        ListNode first_1 = new ListNode(1);
        ListNode first_2 = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        
        first.next = first_1;
        first_1.next = first_2;
        first_2.next = second;
        second.next = third;
        
        RemoveDuplicatesFromSortedListII solution = new RemoveDuplicatesFromSortedListII();
        ListNode head = solution.deleteDuplicates(first);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
