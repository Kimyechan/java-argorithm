package com.company.leetcode.add_two_numbers;


public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode head = result;

        int level = 0;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + level;
            if (val >= 10) {
                val -= 10;
                result.val = val;
                level = 1;
            } else {
                result.val = val;
                level = 0;
            }

            l1 = l1.next;
            l2 = l2.next;

            if (l1 != null || l2 != null) {
                result.next = new ListNode();
                result = result.next;
            }
        }

        while (l1 != null) {
            int val = l1.val + level;
            if (val >= 10) {
                val -= 10;
                result.val = val;
                level = 1;
            } else {
                result.val = val;
                level = 0;
            }

            l1 = l1.next;
            if (l1 != null) {
                result.next = new ListNode();
                result = result.next;
            }
        }

        while (l2 != null) {
            int val = l2.val + level;
            if (val >= 10) {
                val -= 10;
                result.val = val;
                level = 1;
            } else {
                result.val = val;
                level = 0;
            }

            l2 = l2.next;
            if (l2 != null) {
                result.next = new ListNode();
                result = result.next;
            }
        }

        if (level == 1) {
            result.next = new ListNode(1);
        }

        return head;
    }
}

