package com.company.leetcode.l21;

public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        ListNode head = temp;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                break;
            }
            if (l2 == null) {
                temp.next = l1;
                break;
            }

            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        return head.next;
    }
}
