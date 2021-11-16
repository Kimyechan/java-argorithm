package com.company.leetcode.reversed_linked_list;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;

        while (node.next != null) {
            list.add(node);
            node = node.next;
        }
        list.add(node);

        int size = list.size();
        for (int i = size - 1; i > 0; i--) {
            ListNode first = list.get(i);
            ListNode second = list.get(i - 1);

            first.next = second;
        }
        list.get(0).next = null;
        head = list.get(size - 1);

        return head;
    }
}
