package com.scuyjzh.linkedlist;

/**
 * 反转一个单链表。
 *
 * @author scuyjzh
 * @version 1.0
 */
class ReverseLinkedList {
    /**
     * Approach #1 (Iteration - Forward)
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * Approach #2 (Recursion - Backward)
     */
    public ListNode reverseList2(ListNode head) {
        return helper(head);
    }

    private ListNode helper(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = helper(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        System.out.println(ListNode.toString(solution.reverseList1(ListNode.initLinkedList("[1,2,3,4,5]"))));
        System.out.println(ListNode.toString(solution.reverseList2(ListNode.initLinkedList("[1,2,3,4,5]"))));
    }
}
