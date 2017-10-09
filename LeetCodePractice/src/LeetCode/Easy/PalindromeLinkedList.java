package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up:
 * Could you do it in O(n) time and O(1) space?

 * @author ASUS-PC
 *
 */
public class PalindromeLinkedList {
    
    public boolean isPalindrome(ListNode head) {

		if (head == null) {
			return true;
		}

		if (head.next == null) {
			return true;
		}

		ListNode slow = head;
		ListNode fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode newHead = slow.next;
		ListNode pre = null;
		ListNode cur = newHead;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		newHead = pre;

		while (head != null && newHead != null) {
			if (head.val != newHead.val) {
				return false;
			}

			head = head.next;
			newHead = newHead.next;
		}

		return true;
    }
    
    public static void main(String[] args) {
    	ListNode l1 = new ListNode(1);
    	ListNode l2 = new ListNode(2);
    	ListNode l3 = new ListNode(3);
    	ListNode l4 = new ListNode(4);
    	ListNode l5 = new ListNode(1);
    	
    	l1.next = l2;
    	l2.next = l3;
    	l3.next = l4;
    	l4.next = l5;
    	
    	PalindromeLinkedList solution = new PalindromeLinkedList();
    	
    	System.out.println(solution.isPalindrome(l1));
    }
}
