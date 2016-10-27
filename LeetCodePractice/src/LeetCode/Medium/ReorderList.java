package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
 * reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * @author WinnieZhao
 *
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        
        // step 1. cut the list to two halves
        // prev will be the tail of 1st half
        // slow will be the head of 2nd half
        ListNode prev = null, slow = head, fast = head, l1 = head;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        prev.next = null;
        
        // step 2. reverse the 2nd half
        ListNode l2 = reverse(slow);
        
        // step 3. merge the two halves
        merge(l1, l2);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head, next = null;
        
        while (curr != null) {
          next = curr.next;
          curr.next = prev;
          prev = curr;
          curr = next;
        }
        
        return prev;
      }
      
    private void merge(ListNode l1, ListNode l2) {
        while (l1 != null) {
            ListNode n1 = l1.next, n2 = l2.next;
            l1.next = l2;
          
            if (n1 == null)
                break;
              
            l2.next = n1;
            l1 = n1;
            l2 = n2;
        }
    }
      
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third= new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        
        ReorderList solution = new ReorderList();
        solution.reorderList(first);
        while (first != null) {
            System.out.println(first.val);
            first = first.next;
        }
    }
}
