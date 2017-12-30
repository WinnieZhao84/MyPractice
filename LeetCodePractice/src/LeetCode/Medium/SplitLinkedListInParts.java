package LeetCode.Medium;

import LeetCode.Helper.ListNode;

import java.util.Arrays;

/**
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive
 * linked list "parts".
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1.
 * This may lead to some parts being null.
 *
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size
 * greater than or equal parts occurring later.
 *
 * Return a List of ListNode's representing the linked list parts that are formed.
 *
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 * Example 1:
 * Input:
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 *
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2, root.next.next.val = 3, and root.next.next.next = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 *
 * Example 2:
 * Input:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size
 * than the later parts.
 *
 * Note:
 * The length of root will be in the range [0, 1000].
 * Each value of a node in the input will be an integer in the range [0, 999].
 * k will be an integer in the range [1, 50].

 * Created by WinnieZhao on 12/30/2017.
 */
public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode root, int k) {

        ListNode[] res = new ListNode[k];
        ListNode head = root;
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }

        head = root;
        int size = length / k;
        int remain = length % k;

        /**
         * Now for each part, we have calculated how many nodes that part will have: size + (i < remain ? 1 : 0).
         */
        int i=0;
        ListNode pre = null;
        while (i<k) {
            res[i] = head;

            for (int j=0; j<size + (i < remain ? 1 : 0); j++) {
                if (head != null) {
                    pre = head;
                    head = head.next;
                }
            }
            if (pre != null) {
                pre.next = null;
            }
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        SplitLinkedListInParts solution = new SplitLinkedListInParts();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        System.out.println(Arrays.toString(solution.splitListToParts(l1, 5)));

        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(6);
        l.next.next.next.next.next.next = new ListNode(7);
        l.next.next.next.next.next.next.next = new ListNode(8);
        l.next.next.next.next.next.next.next.next= new ListNode(9);
        l.next.next.next.next.next.next.next.next.next = new ListNode(10);

        System.out.println(Arrays.toString(solution.splitListToParts(l, 3)));
    }
}
