package LeetCode.Hard;

import LeetCode.Helper.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Created by WinnieZhao on 4/20/2017.
 */
public class MergeKSortedLists {

    /**
     * Suppose the total number of nodes is n The total time complexity if (n * log k).
     * For a priority queue, insertion takes logK time.
     * n means the total elements and k means the size of list
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        ListNode head = null;
        ListNode tail = null;

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();

            if (head != null) {
                tail.next = node;
                tail = tail.next;
            }
            else {
                head = node;
                tail = node;
            }

            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        //[[1,2,2],[1,1,2]]
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(1);
        ListNode listNode6 = new ListNode(2);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode[] lists = {listNode1, listNode4};

        MergeKSortedLists solution = new MergeKSortedLists();
        ListNode head = solution.mergeKLists(lists);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
