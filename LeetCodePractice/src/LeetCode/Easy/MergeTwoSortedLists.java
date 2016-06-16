package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should 
 * be made by splicing together the nodes of the first two lists.
 * 
 * @author ASUS-PC
 *
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
    	if (l1 == null && l2 == null) return null;
    	else if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
    	
    	ListNode head = null;
    	ListNode result = null;
    	if (l1.val <= l2.val) {
    		head = l1;
    		l1 = l1.next;
    	}
    	else {
    		head = l2;
    		l2 = l2.next;
    	}
    	result = head;
    	
    	while (l1 != null || l2 != null) {
    		if (l1 == null) {
    			head.next = l2;
    			break;
    		}
    		else if (l2 == null) {
    			head.next = l1;
    			break;
    		}
        	if (l1.val <= l2.val) {
        		head.next = l1;
        		l1 = l1.next;
        	}
        	else {
        		head.next = l2;
        		l2 = l2.next;
        	}
        	head = head.next;
    	}
    	return result;
    }
    
    public ListNode mergeTwoLists_Recursive(ListNode l1, ListNode l2) {

        ListNode start;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        if(l1.val < l2.val){
            start = l1;
            start.next = mergeTwoLists_Recursive(l1.next, l2);
        }
        else{
            start = l2;
            start.next = mergeTwoLists_Recursive(l1, l2.next);
        }
        return start;
    }
    
    public static void main(String[] args) {
    	MergeTwoSortedLists solution = new MergeTwoSortedLists();
    	
    	ListNode l1 = new ListNode(2);
    	ListNode l2 = new ListNode(2);
    	
    	ListNode l1_1 = new ListNode(3);
    	l1.next = l1_1;
    	
    	ListNode l2_1 = new ListNode(2);
    	l2.next = l2_1;
    	
    	ListNode l2_2 = new ListNode(5);
    	l2_1.next = l2_2;
        
        ListNode result = solution.mergeTwoLists(l1, l2);
        while (result != null) {
        	System.out.println(result.val);
        	result = result.next;
        }
    }
}
