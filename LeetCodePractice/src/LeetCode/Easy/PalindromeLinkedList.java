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
        
    	ListNode slow = head;
    	ListNode fast = head;
    	
    	while (fast != null) {
    		slow = slow.next;
    		
    		// If the linked list count is odd, now slow is in the middle of the list
    		if (fast.next == null) {
    		    break;
    		}
    		// If the linked list count is even, get the last node of the list
        	if (fast.next.next == null) {
        		fast = fast.next;
        		break;
        	}
    		fast = fast.next.next;
    	}
    	
    	// reverse the 2nd half of the linked list
    	ListNode pre = null;
    	ListNode temp = null;
    	while (slow != null) {
    		temp = slow.next;
    		slow.next = pre;
    		pre = slow;
    		slow = temp;
    	}
    	
    	while (fast != null && head != null) {
    		if (fast.val != head.val) {
    			return false;
    		}
    		fast = fast.next;
    		head = head.next;
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	ListNode l1 = new ListNode(1);
    	ListNode l2 = new ListNode(2);
    	ListNode l3 = new ListNode(3);
    	ListNode l4 = new ListNode(2);
    	ListNode l5 = new ListNode(1);
    	
    	l1.next = l2;
    	l2.next = l3;
    	l3.next = l4;
    	l4.next = l5;
    	
    	PalindromeLinkedList solution = new PalindromeLinkedList();
    	
    	System.out.println(solution.isPalindrome(l1));
    }
}
