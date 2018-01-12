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

    /**
     * Divide and Conquer solution
     * Time Complexity is the same as Priority Queue solution
     * Space Complexity is O(1)
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists){
            return partion(lists,0,lists.length-1);
        }

        public ListNode partion(ListNode[] lists,int s,int e){
            if (s==e) {
                return lists[s];
            }
            if (s<e) {
                int q = s + (e-s)/2;
                ListNode l1 = partion(lists, s, q);
                ListNode l2 = partion(lists,q+1, e);
                return merge(l1,l2);
            }
            else
                return null;
        }

        //This function is from Merge Two Sorted Lists.
        public ListNode merge(ListNode l1, ListNode l2){
            if(l1==null) return l2;
            if(l2==null) return l1;
            if (l1.val < l2.val){
                l1.next = merge(l1.next, l2);
                return l1;
            }
            else{
                l2.next = merge(l1, l2.next);
                return l2;
            }
        }
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
