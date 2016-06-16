package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

import LeetCode.Helper.ListNode;

/**
 * Reverse a singly linked list.
 * 
 * @author ASUS-PC
 *
 */
public class ReverseLinkedList {
	// iteratively 
    public ListNode reverseList_iterative(ListNode head) {
        
    	if (head == null) return head;
    	Map<ListNode, ListNode> relation = new HashMap<ListNode, ListNode>();
    	
    	ListNode oldHead = head;
    	ListNode last = null;
    	while (head.next != null) {
    		relation.put(head, head.next);
    		head = head.next;
    	}
    	last = head;
    	
    	oldHead.next = null;
    	for (ListNode key : relation.keySet()) {
    		ListNode node = relation.get(key);
    		node.next = key;
    	}
    	
    	return last;
    }
    
    public ListNode reverseList_iterative2(ListNode head) {
    	if (head == null) return head;
    	
    	ListNode node = head, pre = null, temp = null;
    	while (node != null) {
    		
            temp = node.next;
            node.next = pre;
            pre = node;
            node = temp;
    	}
    	
    	return pre;
    }
    
    // recursively
    public ListNode reverseList_recursive(ListNode head) {
        return null;
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
    	
    	ListNode newHead = solution.reverseList_iterative2(head);
    	while (newHead.next != null) {
    		System.out.print(newHead.next.val);
    		newHead = newHead.next;
    	}
    }
}
