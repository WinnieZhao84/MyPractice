package LeetCode.Easy;

import LeetCode.Helper.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example,
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, 
 * only nodes itself can be changed.

 * @author ASUS-PC
 *
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode second = head.next;
        ListNode third = second.next;

        second.next = head;
        head.next = swapPairs(third);

        return second;
    }

	class Solution {
		public ListNode swapPairs(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}

			ListNode cur = head;
			ListNode next = head.next;

			if (next != null) {
				cur.next = this.swapPairs(next.next);
			}
			else {
				cur.next = null;
			}

			next.next = cur;


			return next;
		}
	}
    
    public static void main(String[] args) {
    	SwapNodesInPairs solution = new SwapNodesInPairs();
    	
    	ListNode head = new ListNode(1);
    	ListNode l1 = new ListNode(2);
    	ListNode l2 = new ListNode(3);
    	ListNode l3 = new ListNode(4);
    	ListNode l4 = new ListNode(5);

    	head.next = l1;
    	l1.next = l2;
    	l2.next = l3;
    	l3.next = l4;
        
    	ListNode result = solution.swapPairs(head);
        while (result != null) {
        	System.out.println(result.val);
        	result = result.next;
        }
    }
}
