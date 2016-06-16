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
    	if (head == null) {
    		return head;
    	}
        
    	ListNode newHead = head;
    	while (head.next != null) {
    		if (head.val == head.next.val) {
    			head.next = head.next.next;
    		}
    		else {
        		head = head.next;
    		}
    		if (head == null) {
    			break;
    		}
    	}
    	return newHead;
    }
}
