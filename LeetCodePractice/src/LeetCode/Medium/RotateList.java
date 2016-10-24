package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * 
 * For example:
 * 
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * 
 * @author WinnieZhao
 *
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) return head;
        
        ListNode node = head;
        ListNode tail = null;
        
        int count = 0;
        while (node != null) {
            count++;
            if (node.next == null) {
                tail = node;
            }
            node = node.next;
        }
        
        k = k % count; 
        if (k==0) return head;
        
        int i=0;
        ListNode pre = null;
        node = head;
        while (head != null) {
            i++;
            if (i == count - k) {
                pre = head;
                head = head.next;
                pre.next = null;
                tail.next = node;
                break;
            }
            else {
                head = head.next;
            }

        }
        
        return head;
    }
    
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        
        RotateList solution = new RotateList();
        ListNode head = solution.rotateRight(first, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        node.next = node1;
        
        ListNode head1 = solution.rotateRight(node, 3);
        while (head1 != null) {
            System.out.println(head1.val);
            head1 = head1.next;
        }
    }
}
