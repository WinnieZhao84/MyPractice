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

    /**
     * Since n may be a large number compared to the length of list. So we need to know the length of linked list.
     * After that, move the list after the (l-k%l )th node to the front to finish the rotation.
     *
     * Ex: {1,2,3} k=2 Move the list after the 1st node to the front
     * Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.
     *
     * So the code has three parts.
     * Get the length
     * Move to the (l-n%l)th node
     * Do the rotation

     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode dummy=new ListNode(0);
        dummy.next=head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        int len = 0;
        while (fast.next != null) {
            fast = fast.next;
            len++;
        }

        k = len - k % len;
        while(k>0) {
            slow = slow.next;
            k--;
        }

        ListNode newHead = slow.next == null ? head : slow.next;
        fast.next = head;
        slow.next = null;

        return newHead;
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
        ListNode head = solution.rotateRight(first, 5);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        node.next = node1;
        
        ListNode head1 = solution.rotateRight(node, 2);
        while (head1 != null) {
            System.out.println(head1.val);
            head1 = head1.next;
        }
    }
}
