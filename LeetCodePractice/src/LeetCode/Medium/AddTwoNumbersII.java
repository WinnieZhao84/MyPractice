package LeetCode.Medium;

import java.util.Stack;

import LeetCode.Helper.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 8 -> 0 -> 7

 * @author WinnieZhao
 *
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();

            int val = num1 + num2 + carry;
            carry = val / 10;
            val = val % 10;

            ListNode cur = new ListNode(val);
            cur.next = head;
            head = cur;
        }

        if (carry != 0) {
            ListNode cur = new ListNode(carry);
            cur.next = head;
            head = cur;
        }

        return head;
    }
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        
        ListNode l2 = new ListNode(5);
        
        AddTwoNumbersII solution = new AddTwoNumbersII();
        ListNode res = solution.addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        
    }
}
