package LeetCode.Medium;

import LeetCode.Helper.ListNode;

import java.util.Stack;

/**
 * 369
 *
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example: Input: 1->2->3
 * Output: 1->2->4

 * Created by WinnieZhao on 2017/3/29.
 */
public class PlusOneLinkedList {

    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }

        Stack<ListNode> stack = new Stack<>();

        ListNode node = head;
        while (node != null) {
            stack.add(node);
            node = node.next;
        }

        Integer addon = 0;
        boolean tail = true;
        while (!stack.isEmpty()) {
            ListNode cur = stack.pop();
            cur.val = tail ? cur.val + 1 + addon : cur.val + addon;
            if (cur.val == 10) {
                cur.val = 0;
                addon = 1;

                if (!stack.isEmpty()) stack.peek().next = cur;
            }
            else {
                break;
            }
            tail = false;
        }

        if (head.val == 0) {
            ListNode newHead = new ListNode(addon);
            newHead.next = head;
            return newHead;
        }
        else {
            return head;
        }
    }

    public ListNode plusOne_noExtraSpace(ListNode head) {
        if (head == null) {
            return head;
        }

        this.dfs(head);

        if (sum != 0) {
            ListNode newHead = new ListNode(sum);
            newHead.next = head;
            head = newHead;
        }

        return head;
    }

    int sum = 0;
    private void dfs(ListNode head) {
        if (head == null) {
            sum = 1;
            return;
        }
        this.dfs(head.next);

        int val = head.val + sum;
        head.val = val % 10;
        sum = val / 10;
    }



    public static void main(String[] args) {
        PlusOneLinkedList solution = new PlusOneLinkedList();

        ListNode head = new ListNode(9);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);

        ListNode res = solution.plusOne_noExtraSpace(head);

        while(res != null) {
            System.out.print(res.val);
            System.out.print("->");
            res = res.next;
        }
    }

}
