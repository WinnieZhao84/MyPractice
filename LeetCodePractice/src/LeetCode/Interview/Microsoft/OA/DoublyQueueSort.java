package LeetCode.Interview.Microsoft.OA;

/**
 * 双向链表只包含 0 和 1,排序,复杂度要求 O(n)
 *
 * Created by WinnieZhao on 2/25/2018.
 */
public class DoublyQueueSort {

    public static class Node {
        Node next;
        Node pre;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node dummy = new Node(-1);
        dummy.next = head;
        head.pre = dummy;

        int len = 0;
        // Find the tail
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        Node tail = cur;

        int i=0;
        int j=len;
        while (i < j) {
            if (head.val == 0) {
                head = head.next;
                i++;
                continue;
            }
            if (tail.val == 1) {
                tail = tail.pre;
                j--;
                continue;
            }

            if (head.val == 1 && tail.val == 0) {
                int val = head.val;
                head.val = tail.val;
                tail.val = val;
                i++;
                j--;
            }
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        DoublyQueueSort solution = new DoublyQueueSort();

        Node head = new Node(1);

        Node n1 = new Node(0);
        n1.pre = head;
        head.next = n1;

        Node n2 = new Node(1);
        n2.pre = n1;
        n1.next = n2;

        Node n3 = new Node(0);
        n3.pre = n2;
        n2.next = n3;

        Node n4 = new Node(1);
        n3.next = n4;
        n4.pre = n3;

        Node n = head;
        while (n != null) {
            System.out.print(n.val);
            n = n.next;
        }

        System.out.println("");

        Node res = solution.sort(head);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
}
