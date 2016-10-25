package LeetCode.Medium;

import LeetCode.Helper.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example,
 * Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 * 
 * @author WinnieZhao
 *
 */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        
        if (head == null || head.next == null) return head;
        
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        while(head != null) {
            if (head.val < x) {
                cur1.next = head;
                cur1 = cur1.next;
            }
            else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        
        cur2.next = null;
        cur1.next = dummy2.next;
        
        return dummy1.next;
    }
    
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(4);
        ListNode third= new ListNode(3);
        ListNode fourth = new ListNode(2);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(2);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        
        PartitionList solution = new PartitionList();
        ListNode head = solution.partition(first, 3);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}
