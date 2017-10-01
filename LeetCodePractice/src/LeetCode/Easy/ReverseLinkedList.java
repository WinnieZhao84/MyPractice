package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Reverse a singly linked list.
 * 
 * @author ASUS-PC
 *
 */
public class ReverseLinkedList {
    
    public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode cur = head;
		ListNode pre = null;
		ListNode next = null;

		while (cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		return pre;
	}
    
    public static void main(String[] args) {
    	ReverseLinkedList solution = new ReverseLinkedList();
    	
    	ListNode head = new ListNode(1);
    	ListNode two = new ListNode(2);
    	ListNode three = new ListNode(3);
    	ListNode four = new ListNode(4);
    	ListNode five = new ListNode(5);
    	
    	head.next = two;
    	two.next = three;
    	three.next = four;
    	four.next = five;
    	
    	ListNode newHead = solution.reverseList(head);
    	while (newHead != null) {
    		System.out.print(newHead.val);
    		newHead = newHead.next;
    	}
    }
}
