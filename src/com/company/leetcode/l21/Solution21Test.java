package com.company.leetcode.l21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution21Test {
    @Test
    @DisplayName("두 개의 정렬된 링크드 리스트를 정렬해서 합치기")
    public void mergeTwoSortedLists() {
//        Input: l1 = [1,2,4], l2 = [1,3,4]
//        Output: [1,1,2,3,4,4]
        // given
        ListNode l1 = new ListNode(1);
        ListNode h1 = l1;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(4);
        l1 = l1.next;

        ListNode l2 = new ListNode(1);
        ListNode h2 = l2;
        l2.next = new ListNode(3);
        l2 = l2.next;
        l2.next = new ListNode(4);
        l2 = l2.next;

        // when
        Solution21 solution21 = new Solution21();
        ListNode result = solution21.mergeTwoLists(h1, h2);

        // then
        for (int val : new int[]{1, 1, 2, 3, 4, 4}) {
            assertEquals(result.val, val);
            result = result.next;
        }
    }
}