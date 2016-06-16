package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 * @author ASUS-PC
 *
 */
public class RemoveNthNodeFromEndOfList {
    
	public ListNode removeNthFromEnd(ListNode head, int n) {
        
    	int count = 0;
    	ListNode node = head;
    	while (node != null) {
    		count++;
    		node = node.next;
    	}
    	
        //if remove first node
        int fromStart = count - n +1;
        if(fromStart==1)
            return head.next;
     
        //remove non-first node    
        node = head;
        int i=0;
        while(node!=null){
            i++;
            if(i==fromStart-1){
            	node.next = node.next.next;
            }
            node=node.next;
        }
     
        return head;
    }
	
	public ListNode removeNthFromEnd_solution2(ListNode head, int n) {
		if(head == null)
	        return null;
	 
	    ListNode fast = head;
	    ListNode slow = head;
	 
	    for(int i=0; i<n; i++){
	        fast = fast.next;
	    }
	 
	    //if remove the first node
	    if(fast == null){
	        head = head.next;
	        return head;
	    }
	 
	    while(fast.next != null){
	        fast = fast.next;
	        slow = slow.next;
	    }
	 
	    slow.next = slow.next.next;
	 
	    return head;
	}
    
    public static void main(String[] args) {
    	ListNode a = new ListNode(1);
    	ListNode b = new ListNode(2);
    	ListNode c = new ListNode(3);
    	ListNode d = new ListNode(4);
    	a.next = b;
    	b.next = c;
    	c.next = d;
    	
    	RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();
    	solution.removeNthFromEnd(a, 2);
    }
}
