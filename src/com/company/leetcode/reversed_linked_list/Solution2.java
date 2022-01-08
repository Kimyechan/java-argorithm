package com.company.leetcode.reversed_linked_list;

public class Solution2 {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[6];

        for (int i = 1; i <= 5; i++) {
            nodes[i] = new ListNode(i);
        }

        for (int i = 1; i < 5; i++) {
            nodes[i].next = nodes[i + 1];
        }

        ListNode result = reverseList(nodes[1]);
    }
}
