package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5

 * @author ASUS-PC
 *
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
    	
    	if (head == null) {
    		return head;
    	}
    	ListNode node = head;
    	ListNode previous = null;
    	
    	if (head.val == val) {
    		while (head != null) {
    			if (head.val == val) {
    				head = head.next;
    			}
    			else {
    				break;
    			}
    		}
    	}
        while (node != null) {

            if (previous != null && node.val == val) {
            	previous.next = node.next;
            }
            else {
                previous = node;
            }

            node = node.next;
        }
        
        return head;
    }
    
    public ListNode removeElements_better(ListNode head, int val) {

        if (head == null) {
            return null;
        }
        
        while (head.val == val) {
            head = head.next;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;    
            } else {
                current = current.next;
            }
        }
        return head;
    }
    
    public static void main(String[] args) {
    	RemoveLinkedListElements solution = new RemoveLinkedListElements();
    	
    	ListNode head = new ListNode(1);
    	ListNode l1 = new ListNode(2);
    	head.next = l1;
    	ListNode l2 = new ListNode(2);
    	l1.next = l2;
    	ListNode l3 = new ListNode(1);
    	l2.next = l3;
    	ListNode l4 = new ListNode(2);
    	l3.next = l4;
    	
    	ListNode result = solution.removeElements(head, 1);
    	
    	while (result != null) {
    		System.out.println(result.val);
    		result = result.next;
    	}
    }
}
