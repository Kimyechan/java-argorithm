package com.company.leetcode.add_two_numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Input: l1 = [2,4,3], l2 = [5,6,4]
//        Output: [7,0,8]
//        Explanation: 342 + 465 = 807.

class SolutionTest {
    @Test
    @DisplayName("두 수의 합")
    public void addTwoNumbers() {
        // given
        ListNode l1 = new ListNode(2);
        ListNode l1Head = l1;
        l1.next = new ListNode(4); l1 = l1.next;
        l1.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        ListNode l2Head = l2;
        l2.next = new ListNode(6); l2 = l2.next;
        l2.next = new ListNode(4);


        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1Head, l2Head);

        assertEquals(result.val, 7);
        result = result.next;
        assertEquals(result.val, 0);
        result = result.next;
        assertEquals(result.val, 8);
    }
}