package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 ¡ú a2
                                                   ¨K
                     c1 ¡ú c2 ¡ú c3
                                                    ¨J            
B:     b1 ¡ú b2 ¡ú b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

 * @author ASUS-PC
 *
 */
public class IntersectionOfTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
        ListNode p1 = headA;
        ListNode p2 = headB;
        
		int aLength = 1;
		while(p1.next != null) {
			aLength++;
			p1 = p1.next;
		}
		
		int bLength = 1;
		while(p2.next != null) {
			bLength++;
			p2 = p2.next;
		}
        
		if (p1 != p2) {
			return null;
		}
		
		int diff = Math.abs(aLength - bLength);
		
		if (aLength >= bLength) {
			p1 = headA;
			p2 = headB;
		}
		else {
			p1 = headB;
			p2 = headA;
		}
        
		while (diff > 0) {
			p1 = p1.next;
			diff--;
		}
		
		while (p1.next != null && p2.next != null && p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p1;
    }

}
